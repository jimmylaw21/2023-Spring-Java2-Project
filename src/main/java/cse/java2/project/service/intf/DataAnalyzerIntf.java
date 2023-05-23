package cse.java2.project.service.intf;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public interface DataAnalyzerIntf {
  // Number of Answers
  String getPercentageOfQuestionsWithoutAnswers();
  double getAverageNumberOfAnswers();
  int getMaximumNumberOfAnswers();
  Map<Integer,Double> getAverageNumberDistributionOfAnswers();
  Map<Integer,Integer> getMaximumNumberDistributionOfAnswers();
  Map<Integer, Integer> getDistributionOfNumberOfAnswers();

  // Accepted Answers
  String getPercentageOfQuestionsWithAcceptedAnswers();
  Map<Integer, Integer> getDistributionOfQuestionResolutionTime();
  String getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes();

  // Tags
  Map<String, Integer> getFrequentTagsWithJava();
  Map<String, Integer> getMostUpvotedTagsOrTagCombinations();
  Map<String, Integer> getMostViewedTagsOrTagCombinations();

  // Users
  List<String> getDistributionOfUserParticipation();
  List<String> getMostActiveUsers();
}

