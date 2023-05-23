package cse.java2.project.controller;

import cse.java2.project.service.intf.DataAnalyzerIntf;
import cse.java2.project.service.intf.JavaApiIdentifierIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")  // 使用@RequestMapping定义所有请求的公共路径
public class RestApiController {

  private final DataAnalyzerIntf dataAnalyzer;
  private final JavaApiIdentifierIntf javaApiIdentifier;

  @Autowired
  public RestApiController(DataAnalyzerIntf dataAnalyzer, JavaApiIdentifierIntf javaApiIdentifier) {
    this.dataAnalyzer = dataAnalyzer;
    this.javaApiIdentifier = javaApiIdentifier;
  }

  @GetMapping("/percentage-of-questions-without-answers")
  public String getPercentageOfQuestionsWithoutAnswers() {
    return dataAnalyzer.getPercentageOfQuestionsWithoutAnswers();
  }

  @GetMapping("/average-number-of-answers")
  public double getAverageNumberOfAnswers() {
    return dataAnalyzer.getAverageNumberOfAnswers();
  }

  @GetMapping("/maximum-number-of-answers")
  public int getMaximumNumberOfAnswers() {
    return dataAnalyzer.getMaximumNumberOfAnswers();
  }

  @GetMapping("/distribution-of-number-of-answers")
  public Map<Integer, Integer> getDistributionOfNumberOfAnswers() {
    return dataAnalyzer.getDistributionOfNumberOfAnswers();
  }

  @GetMapping("/percentage-of-questions-with-accepted-answers")
  public String getPercentageOfQuestionsWithAcceptedAnswers() {
    return dataAnalyzer.getPercentageOfQuestionsWithAcceptedAnswers();
  }

  @GetMapping("/distribution-of-question-resolution-time")
  public Map<Integer, Integer> getDistributionOfQuestionResolutionTime() {
    return dataAnalyzer.getDistributionOfQuestionResolutionTime();
  }

  @GetMapping("/percentage-of-questions-with-non-accepted-answers-having-more-upvotes")
  public String getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes() {
    return dataAnalyzer.getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes();
  }

  @GetMapping("/frequent-tags")
  public Map<String, Integer> getFrequentTagsWithJava() {
    return dataAnalyzer.getFrequentTagsWithJava();
  }

  @GetMapping("/most-upvoted-tags")
  public Map<String, Integer> getMostUpvotedTagsOrTagCombinations() {
    return dataAnalyzer.getMostUpvotedTagsOrTagCombinations();
  }

  @GetMapping("/most-viewed-tags")
  public Map<String, Integer> getMostViewedTagsOrTagCombinations() {
    return dataAnalyzer.getMostViewedTagsOrTagCombinations();
  }

  @GetMapping("/distribution-of-user-participation")
  public List<String> getDistributionOfUserParticipation() {
    return dataAnalyzer.getDistributionOfUserParticipation();
  }

  @GetMapping("/most-active-users")
  public List<String> getMostActiveUsers() {
    return dataAnalyzer.getMostActiveUsers();
  }

  @GetMapping("/most-used-JavaApi")
  public Map<String, Integer> getMostUsedJavaApi() {
    return javaApiIdentifier.getMostUsedJavaApi();
  }
}