package cse.java2.project.service.intf;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public interface JavaApiIdentifierIntf {

  List<String> extractCodeSnippets(String text);
  List<String> extractClassAndMethodNames(String codeSnippet);
  Map<String, Integer> getMostUsedJavaApi();
}
