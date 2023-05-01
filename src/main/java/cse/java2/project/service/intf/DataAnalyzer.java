package cse.java2.project.service.intf;

import java.util.List;
import java.util.Map;

public interface DataAnalyzer {
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
  Map<String, Integer> getFrequentTagsWithJava(); //词云
  Map<String, Integer> getMostUpvotedTagsOrTagCombinations(); //词饼
  Map<String, Integer> getMostViewedTagsOrTagCombinations();  //词饼

  // Users
  Map<Integer, Integer> getDistributionOfUserParticipation();
  List<String> getMostActiveUsers();
}

