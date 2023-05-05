package cse.java2.project.service.impl;

import cse.java2.project.service.intf.DataAnalyzerIntf;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataAnalyzer implements DataAnalyzerIntf {

  @Override
  public double getPercentageOfQuestionsWithoutAnswers() {
    return 0;
  }

  @Override
  public double getAverageNumberOfAnswers() {
    return 0;
  }

  @Override
  public int getMaximumNumberOfAnswers() {
    return 0;
  }

  @Override
  public Map<Integer, Integer> getDistributionOfNumberOfAnswers() {
    return null;
  }

  @Override
  public double getPercentageOfQuestionsWithAcceptedAnswers() {
    return 0;
  }

  @Override
  public Map<Long, Integer> getDistributionOfQuestionResolutionTime() {
    return null;
  }

  @Override
  public double getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes() {
    return 0;
  }

  @Override
  public Map<String, Integer> getFrequentTagsWithJava() {
    return null;
  }

  @Override
  public Map<String, Integer> getMostUpvotedTagsOrTagCombinations() {
    return null;
  }

  @Override
  public Map<String, Integer> getMostViewedTagsOrTagCombinations() {
    return null;
  }

  @Override
  public Map<Integer, Integer> getDistributionOfUserParticipation() {
    return null;
  }

  @Override
  public List<String> getMostActiveUsers() {
    return null;
  }
}
