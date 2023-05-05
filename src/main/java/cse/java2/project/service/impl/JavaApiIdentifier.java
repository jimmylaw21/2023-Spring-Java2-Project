package cse.java2.project.service.impl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import cse.java2.project.service.intf.JavaApiIdentifierIntf;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JavaApiIdentifier implements JavaApiIdentifierIntf {

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
    return null;
  }
}
