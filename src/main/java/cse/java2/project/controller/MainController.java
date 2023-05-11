package cse.java2.project.controller;

import cse.java2.project.service.intf.DataAnalyzerIntf;
import cse.java2.project.service.intf.JavaApiIdentifierIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

  /**
   * This method is called when the user requests the root URL ("/") or "/demo".
   * In this demo, you can visit localhost:9090 or localhost:9090/demo to see the result.
   * @return the name of the view to be rendered
   * You can find the static HTML file in src/main/resources/templates/demo.html
   */
  private final DataAnalyzerIntf dataAnalyzer;

  private final JavaApiIdentifierIntf javaApiIdentifier;

  @Autowired
  public MainController(DataAnalyzerIntf dataAnalyzer, JavaApiIdentifierIntf javaApiIdentifier) {
    this.dataAnalyzer = dataAnalyzer;
    this.javaApiIdentifier = javaApiIdentifier;
  }


  @GetMapping({"/", "/demo"})
  public String demo() {
    return "index";
  }

  @GetMapping("/api/percentage-of-questions-without-answers")
  public @ResponseBody String getPercentageOfQuestionsWithoutAnswers() {
    return dataAnalyzer.getPercentageOfQuestionsWithoutAnswers();
  }

  @GetMapping("/api/average-number-of-answers")
  public @ResponseBody double getAverageNumberOfAnswers() {
    return dataAnalyzer.getAverageNumberOfAnswers();
  }

  @GetMapping("/api/maximum-number-of-answers")
  public @ResponseBody int getMaximumNumberOfAnswers() {
    return dataAnalyzer.getMaximumNumberOfAnswers();
  }

  @GetMapping("/api/distribution-of-number-of-answers")
  public @ResponseBody Map<Integer, Integer> getDistributionOfNumberOfAnswers() {
    return dataAnalyzer.getDistributionOfNumberOfAnswers();
  }

  @GetMapping("/api/percentage-of-questions-with-accepted-answers")
  public @ResponseBody String getPercentageOfQuestionsWithAcceptedAnswers() {
    return dataAnalyzer.getPercentageOfQuestionsWithAcceptedAnswers();
  }

  @GetMapping("/api/distribution-of-question-resolution-time")
  public @ResponseBody Map<Long, Integer> getDistributionOfQuestionResolutionTime() {
    return dataAnalyzer.getDistributionOfQuestionResolutionTime();
  }

  @GetMapping("/api/percentage-of-questions-with-non-accepted-answers-having-more-upvotes")
  public @ResponseBody String getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes() {
    return dataAnalyzer.getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes();
  }

  @GetMapping("/api/frequent-tags")
  public @ResponseBody Map<String, Integer> getFrequentTagsWithJava() {
    Map<String, Integer> result = dataAnalyzer.getFrequentTagsWithJava();
    System.out.println(result);
    return result;
  }

  @GetMapping("/api/most-upvoted-tags")
  public @ResponseBody Map<String, Integer> getMostUpvotedTagsOrTagCombinations() {
    return dataAnalyzer.getMostUpvotedTagsOrTagCombinations();
  }

  @GetMapping("/api/most-viewed-tags")
  public @ResponseBody Map<String, Integer> getMostViewedTagsOrTagCombinations() {
    return dataAnalyzer.getMostViewedTagsOrTagCombinations();
  }

  @GetMapping("/api/distribution-of-user-participation")
  public @ResponseBody Map<Integer, Integer> getDistributionOfUserParticipation() {
    return dataAnalyzer.getDistributionOfUserParticipation();
  }

  @GetMapping("/api/most-active-users")
  public @ResponseBody List<String> getMostActiveUsers() {
    return dataAnalyzer.getMostActiveUsers();
  }

  @GetMapping("/api/most-used-JavaApi")
    public @ResponseBody Map<String, Integer> getMostUsedJavaApi() {
      return javaApiIdentifier.getMostUsedJavaApi();
    }

  //welcome
  @GetMapping("/api/welcome")
  public @ResponseBody String welcome() {
    return "Welcome to the Stack Overflow Data Analyzer!";
  }

}
