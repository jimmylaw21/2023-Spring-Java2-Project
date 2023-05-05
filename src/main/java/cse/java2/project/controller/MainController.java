package cse.java2.project.controller;

import cse.java2.project.service.intf.DataAnalyzerIntf;
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

  @Autowired
  public MainController(DataAnalyzerIntf dataAnalyzer) {
    this.dataAnalyzer = dataAnalyzer;
  }


  @GetMapping({"/", "/demo"})
  public String demo() {
    return "demo";
  }

  @GetMapping("/api/frequent-tags")
  public @ResponseBody Map<String, Integer> getFrequentTagsWithJava() {
    return dataAnalyzer.getFrequentTagsWithJava();
  }

  @GetMapping("/api/most-upvoted-tags")
  public @ResponseBody Map<String, Integer> getMostUpvotedTagsOrTagCombinations() {
    return dataAnalyzer.getMostUpvotedTagsOrTagCombinations();
  }

  @GetMapping("/api/most-viewed-tags")
  public @ResponseBody Map<String, Integer> getMostViewedTagsOrTagCombinations() {
    return dataAnalyzer.getMostViewedTagsOrTagCombinations();
  }

  @GetMapping("/api/percentage-of-questions-without-answers")
  public @ResponseBody double getPercentageOfQuestionsWithoutAnswers() {
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
  public @ResponseBody double getPercentageOfQuestionsWithAcceptedAnswers() {
    return dataAnalyzer.getPercentageOfQuestionsWithAcceptedAnswers();
  }

  @GetMapping("/api/distribution-of-question-resolution-time")
  public @ResponseBody Map<Long, Integer> getDistributionOfQuestionResolutionTime() {
    return dataAnalyzer.getDistributionOfQuestionResolutionTime();
  }

  @GetMapping("/api/percentage-of-questions-with-non-accepted-answers-having-more-upvotes")
  public @ResponseBody double getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes() {
    return dataAnalyzer.getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes();
  }

  @GetMapping("/api/distribution-of-user-participation")
  public @ResponseBody Map<Integer, Integer> getDistributionOfUserParticipation() {
    return dataAnalyzer.getDistributionOfUserParticipation();
  }

  @GetMapping("/api/most-active-users")
  public @ResponseBody List<String> getMostActiveUsers() {
    return dataAnalyzer.getMostActiveUsers();
  }

}
