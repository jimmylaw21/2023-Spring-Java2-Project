package cse.java2.project.service.intf;

import org.springframework.stereotype.Component;

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
  Map<Long, Integer> getDistributionOfQuestionResolutionTime();
  String getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes();

  // Tags
  Map<String, Integer> getFrequentTagsWithJava();
  Map<String, Integer> getMostUpvotedTagsOrTagCombinations();
  Map<String, Integer> getMostViewedTagsOrTagCombinations();

  // Users
  Map<Integer, Integer> getDistributionOfUserParticipation();
  Map<Integer, Integer> getDistributionOfAnswerUserParticipation();
  Map<Integer, Integer> getDistributionOfCommentUserParticipation();
  List<String> getMostActiveUsers();
}

