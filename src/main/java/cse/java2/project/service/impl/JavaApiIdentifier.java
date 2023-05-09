package cse.java2.project.service.impl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import cse.java2.project.service.intf.JavaApiIdentifierIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class JavaApiIdentifier implements JavaApiIdentifierIntf {

  @Autowired
  private StackOverflowThreadMapper stackOverflowThreadMapper;

  @Override
  public List<String> extractCodeSnippets(String text) {
    List<String> codeSnippets = new ArrayList<>();
    Pattern pattern = Pattern.compile("```java\\s+([\\s\\S]+?)```");
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      codeSnippets.add(matcher.group(1));
    }
    return codeSnippets;
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
      System.out.println(body);
      List<String> codeSnippets = extractCodeSnippets(body);
      for (String codeSnippet : codeSnippets) {
        List<String> classNamesAndMethodNames = extractClassAndMethodNames(codeSnippet);
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
