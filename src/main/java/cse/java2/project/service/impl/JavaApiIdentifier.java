package cse.java2.project.service.impl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import cse.java2.project.service.intf.JavaApiIdentifierIntf;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class JavaApiIdentifier implements JavaApiIdentifierIntf {

  String resource = "mybatis-config.xml";
  InputStream inputStream = Resources.getResourceAsStream(resource);
  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  SqlSession sqlSession = sqlSessionFactory.openSession();
  StackOverflowThreadMapper stackOverflowThreadMapper = sqlSession.getMapper(StackOverflowThreadMapper.class);


  @Autowired
  public JavaApiIdentifier(StackOverflowThreadMapper stackOverflowThreadMapper) throws IOException {

  }

  @Override
  public List<String> extractCodeSnippets(String text) {
    List<String> codeSnippets = new ArrayList<>();

    // Match code blocks enclosed with "```" (with or without a language specifier)
    Pattern pattern = Pattern.compile("```(?:\\w*\\s)?([\\s\\S]+?)```");
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      String possibleJavaCode = matcher.group(1);
      if (isJavaCode(possibleJavaCode)) {
        codeSnippets.add(possibleJavaCode);
      }
    }

    // Match inline code marked with "`"
    Pattern patternInline = Pattern.compile("`([^`]+?)`");
    Matcher matcherInline = patternInline.matcher(text);
    while (matcherInline.find()) {
      String possibleJavaCode = matcherInline.group(1);
      if (isJavaCode(possibleJavaCode)) {
        codeSnippets.add(possibleJavaCode);
      }
    }

    // Match code blocks indented with 4 spaces
    Pattern patternIndented = Pattern.compile("(?m)^ {4}(.+)$");
    Matcher matcherIndented = patternIndented.matcher(text);
    while (matcherIndented.find()) {
      String possibleJavaCode = matcherIndented.group(1);
      if (isJavaCode(possibleJavaCode)) {
        codeSnippets.add(possibleJavaCode);
      }
    }

    return codeSnippets;
  }

  // Use JavaParser to check if a string is valid Java code
  private boolean isJavaCode(String text) {
    JavaParser javaParser = new JavaParser();
    ParseResult<?> parseResult = javaParser.parse(text);
    return parseResult.isSuccessful();
  }


  @Override
  public List<String> extractClassAndMethodNames(String codeSnippet) {
    List<String> classNamesAndMethodNames = new ArrayList<>();
    JavaParser javaParser = new JavaParser();
    ParseResult<CompilationUnit> parseResult = javaParser.parse(codeSnippet);

    if (parseResult.isSuccessful()) {
      CompilationUnit compilationUnit = parseResult.getResult().orElse(null);
      if (compilationUnit != null) {
        List<ClassOrInterfaceDeclaration> classDeclarations = compilationUnit.findAll(ClassOrInterfaceDeclaration.class);
        for (ClassOrInterfaceDeclaration classDeclaration : classDeclarations) {
          classNamesAndMethodNames.add(classDeclaration.getNameAsString());
        }

        List<MethodDeclaration> methodDeclarations = compilationUnit.findAll(MethodDeclaration.class);
        for (MethodDeclaration methodDeclaration : methodDeclarations) {
          classNamesAndMethodNames.add(methodDeclaration.getNameAsString());
        }
      }
    }

    return classNamesAndMethodNames;
  }


  @Override
  public Map<String, Integer> getMostUsedJavaApi() {
    Map<String, Integer> apiCounts = new HashMap<>();

    // Get all question, answer, and comment bodies
    List<String> questionBodies = stackOverflowThreadMapper.getAllQuestionBodies();
    List<String> answerBodies = stackOverflowThreadMapper.getAllAnswerBodies();
    List<String> commentBodies = stackOverflowThreadMapper.getAllCommentBodies();


    List<String> allBodies = new ArrayList<>();
    allBodies.addAll(questionBodies);
    allBodies.addAll(answerBodies);
    allBodies.addAll(commentBodies);

    for (String body : allBodies) {
      List<String> codeSnippets = extractCodeSnippets(body);
      for (String codeSnippet : codeSnippets) {
        List<String> classNamesAndMethodNames = extractClassAndMethodNames(codeSnippet);
//        List<String> classNamesAndMethodNames = extractClassAndMethodNames(body);
        for (String name : classNamesAndMethodNames) {
          apiCounts.put(name, apiCounts.getOrDefault(name, 0) + 1);
        }
      }
    }

    // Sort the map by value in descending order
    Map<String, Integer> sortedApiCounts = apiCounts.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    return sortedApiCounts;
  }
}
