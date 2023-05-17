package cse.java2.project.service.impl;

import cse.java2.project.domain.model.dto.Answer;
import cse.java2.project.domain.model.dto.Question;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import cse.java2.project.service.intf.DataAnalyzerIntf;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataAnalyzer implements DataAnalyzerIntf {

  String resource = "mybatis-config.xml";
  InputStream inputStream = Resources.getResourceAsStream(resource);
  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  SqlSession sqlSession = sqlSessionFactory.openSession();
  StackOverflowThreadMapper stackOverflowThreadMapper = sqlSession.getMapper(StackOverflowThreadMapper.class);

  @Autowired
  public DataAnalyzer(StackOverflowThreadMapper stackOverflowThreadMapper) throws IOException {

  }

  @Override
  public String getPercentageOfQuestionsWithoutAnswers() {
    double questionWithoutAnswersCount=0;
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();
    for (Question question : allQuestions) {
      if (question.getAnswerCount()==0) {
        questionWithoutAnswersCount++;
      }
    }
    double questionCount=allQuestions.size();
    NumberFormat num=NumberFormat.getPercentInstance();
    return num.format(questionWithoutAnswersCount/questionCount);
  }

  @Override
  public double getAverageNumberOfAnswers() {
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();
    double answerTotalNumber=0;
    for (Question question : allQuestions) {
      answerTotalNumber+=question.getAnswerCount();
    }
    return answerTotalNumber/allQuestions.size();
  }

  @Override
  public int getMaximumNumberOfAnswers() {
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();
    int maxAnswerCount=0;
    for (Question question : allQuestions) {
      if (question.getAnswerCount()>maxAnswerCount) {
        maxAnswerCount=question.getAnswerCount();
      }
    }
    return maxAnswerCount;
  }

  @Override
  public Map<Integer, Integer> getDistributionOfNumberOfAnswers() {
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();
    Map<Integer, Integer> answerCount = new HashMap<>();
    for (Question question : allQuestions) {
      int count=question.getAnswerCount();
      answerCount.put(count, answerCount.getOrDefault(count, 0) + 1);
    }
    return answerCount;
  }

  @Override
  public String getPercentageOfQuestionsWithAcceptedAnswers() {
    List<Integer> questionIds = stackOverflowThreadMapper.getAllQuestionIds();
    double acceptedAnswerCount=0;
    for (Integer questionId : questionIds) {
      List<Answer> answers = stackOverflowThreadMapper.getAnswersByQuestionId(questionId);
      for (Answer answer : answers) {
        if(answer.isAccepted()){
          acceptedAnswerCount++;//one question could only have one accepted answer
        }
      }
    }
    NumberFormat num=NumberFormat.getPercentInstance();
    return num.format(acceptedAnswerCount/questionIds.size());

  }

  @Override
  public Map<Long, Integer> getDistributionOfQuestionResolutionTime() {
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();
    Map<Long, Integer> questionResolutionTime = new HashMap<>();
    for (Question question : allQuestions) {
      long questionTime = question.getCreationDate();
      List<Answer> answers = stackOverflowThreadMapper.getAnswersByQuestionId(question.getQuestionId());
      for (Answer answer : answers) {
        if (answer.isAccepted()) {
          long acceptedAnswerTime = answer.getCreationDate();
          long diff = acceptedAnswerTime - questionTime;
          questionResolutionTime.put(diff, questionResolutionTime.getOrDefault(diff, 0) + 1);
        }
      }
    }
    return questionResolutionTime;
  }

  @Override
  public String getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes() {
    List<Integer> questionIds = stackOverflowThreadMapper.getAllQuestionIds();
    double count=0;
    for (int questionId : questionIds) {
      int acceptedAnswerUpvote=0;
      int otherAnswerMaxUpvote=0;
      List<Answer> answers = stackOverflowThreadMapper.getAnswersByQuestionId(questionId);
      for (Answer answer : answers) {
        if (answer.isAccepted()) {
          acceptedAnswerUpvote=answer.getUpVoteCount();
        }else {
          otherAnswerMaxUpvote=Math.max(otherAnswerMaxUpvote, answer.getUpVoteCount());
        }
      }
      if(acceptedAnswerUpvote!=0&&acceptedAnswerUpvote<otherAnswerMaxUpvote){
        count++;
      }
    }
    NumberFormat num=NumberFormat.getPercentInstance();
    return num.format(count/questionIds.size());
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
      mostActiveUsers.add(stackOverflowThreadMapper.getDisplayNameByOwnerId((String) userData.get("owner_id")));
    }
    return mostActiveUsers;
  }

}
