package cse.java2.project.service.impl;

import cse.java2.project.domain.model.dto.Question;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import cse.java2.project.service.intf.DataAnalyzerIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataAnalyzer implements DataAnalyzerIntf {

  private final StackOverflowThreadMapper stackOverflowThreadMapper;

  @Autowired
  public DataAnalyzer(StackOverflowThreadMapper stackOverflowThreadMapper) {
    this.stackOverflowThreadMapper = stackOverflowThreadMapper;
  }

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
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();

    return allQuestions.stream()
        .filter(q -> q.getTags().contains("java"))
        .flatMap(q -> q.getTags().stream())
        .filter(tag -> !tag.equals("java"))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
  }

  @Override
  public Map<String, Integer> getMostUpvotedTagsOrTagCombinations() {
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();

    return allQuestions.stream()
        .collect(Collectors.toMap(
            q -> String.join(",", q.getTags()),
            Question::getUpVoteCount,
            Integer::sum));
  }


  @Override
  public Map<String, Integer> getMostViewedTagsOrTagCombinations() {
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();

    return allQuestions.stream()
        .collect(Collectors.toMap(
            q -> String.join(",", q.getTags()),
            Question::getViewCount,
            Integer::sum));
  }


  @Override
  public Map<Integer, Integer> getDistributionOfUserParticipation() {
    List<Integer> questionIds = stackOverflowThreadMapper.getAllQuestionIds();
    Map<Integer, Integer> threadParticipationCount = new HashMap<>();

    for (Integer questionId : questionIds) {
      List<String> participants = stackOverflowThreadMapper.getParticipantsByQuestionId(questionId);
      int count = participants.size();
      threadParticipationCount.put(count, threadParticipationCount.getOrDefault(count, 0) + 1);
    }

    return threadParticipationCount;
  }

  @Override
  public List<String> getMostActiveUsers() {
    int limit = 10; // 设定返回的最活跃用户数量，可以根据需要调整
    List<Map<String, Object>> mostActiveUsersData = stackOverflowThreadMapper.getMostActiveUsersWithLimit(limit);
    List<String> mostActiveUsers = new ArrayList<>();

    for (Map<String, Object> userData : mostActiveUsersData) {
      mostActiveUsers.add((String) userData.get("owner_id"));
    }

    return mostActiveUsers;
  }

}
