package cse.java2.project.service.intf;

import java.util.List;
import java.util.Map;

public interface DataAnalyzerIntf {
  // Number of Answers
  double getPercentageOfQuestionsWithoutAnswers();
  double getAverageNumberOfAnswers();
  int getMaximumNumberOfAnswers();
  Map<Integer, Integer> getDistributionOfNumberOfAnswers();

  // Accepted Answers
  double getPercentageOfQuestionsWithAcceptedAnswers();
  Map<Long, Integer> getDistributionOfQuestionResolutionTime();
  double getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes();

  // Tags
  Map<String, Integer> getFrequentTagsWithJava();
  Map<String, Integer> getMostUpvotedTagsOrTagCombinations();
  Map<String, Integer> getMostViewedTagsOrTagCombinations();

  // Users
  Map<Integer, Integer> getDistributionOfUserParticipation();
  List<String> getMostActiveUsers();
}

