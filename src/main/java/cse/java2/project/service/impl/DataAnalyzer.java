package cse.java2.project.service.impl;

import cse.java2.project.domain.model.dto.Answer;
import cse.java2.project.domain.model.dto.Comment;
import cse.java2.project.domain.model.dto.Question;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import cse.java2.project.service.intf.DataAnalyzerIntf;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DataAnalyzer implements DataAnalyzerIntf {

  String resource = "mybatis-config.xml";
  InputStream inputStream = Resources.getResourceAsStream(resource);

  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
  StackOverflowThreadMapper stackOverflowThreadMapper = sqlSessionTemplate.getMapper(StackOverflowThreadMapper.class);

  @Autowired
  public DataAnalyzer(StackOverflowThreadMapper stackOverflowThreadMapper) throws IOException {

  }

  @Override
  public String getPercentageOfQuestionsWithoutAnswers() {
    double questionWithoutAnswersCount = 0;
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();
    for (Question question : allQuestions) {
      if (question.getAnswerCount() == 0) {
        questionWithoutAnswersCount++;
      }
    }
    double questionCount = allQuestions.size();
    double other = questionCount - questionWithoutAnswersCount;
    return questionWithoutAnswersCount + " " + other;
  }

  @Override
  //平均每个问题（thread）有多少条回答
  public double getAverageNumberOfAnswers() {
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();
    double answerTotalNumber = 0;
    for (Question question : allQuestions) {
      answerTotalNumber += question.getAnswerCount();
    }
    return answerTotalNumber / allQuestions.size();
  }

  @Override
  public int getMaximumNumberOfAnswers() {
    List<Question> allQuestions = stackOverflowThreadMapper.getAllQuestions();
    int maxAnswerCount = 0;
    for (Question question : allQuestions) {
      if (question.getAnswerCount() > maxAnswerCount) {
        maxAnswerCount = question.getAnswerCount();
      }
    }
    return maxAnswerCount;
  }

  @Override
  //平均每个问题（thread）有多少条回答
  public Map<Integer, Double> getAverageNumberDistributionOfAnswers() {//按年份分布，年份由问题的创建时间确定
    List<Question> questions = stackOverflowThreadMapper.getAllQuestions();
    Map<Integer, List<Integer>> questionEveryYear = new HashMap<>();
    for (Question question : questions) {
      long timestamp = question.getCreationDate();
      // 将时间戳转换为LocalDateTime对象
      LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
      // 获取年份
      int year = dateTime.getYear();
      questionEveryYear.computeIfAbsent(year, k -> new ArrayList<>());
      questionEveryYear.get(year).add(question.getAnswerCount());
    }
    Map<Integer, Double> avgDistribution = new HashMap<>();
    for (int year : questionEveryYear.keySet()) {
      double sum = questionEveryYear.get(year).stream()
          .reduce(0, Integer::sum);
      avgDistribution.put(year, sum / questionEveryYear.get(year).size());
    }
    avgDistribution = avgDistribution.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    return avgDistribution;
  }

  @Override
  public Map<Integer, Integer> getMaximumNumberDistributionOfAnswers() {//按年份分布
    List<Question> questions = stackOverflowThreadMapper.getAllQuestions();
    Map<Integer, List<Integer>> questionEveryYear = new HashMap<>();
    for (Question question : questions) {
      long timestamp = question.getCreationDate();
      // 将时间戳转换为LocalDateTime对象
      LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
      // 获取年份
      int year = dateTime.getYear();
      questionEveryYear.computeIfAbsent(year, k -> new ArrayList<>());
      questionEveryYear.get(year).add(question.getAnswerCount());
    }
    Map<Integer, Integer> maxDistribution = new HashMap<>();
    for (int year : questionEveryYear.keySet()) {
      int max = Collections.max(questionEveryYear.get(year));
      maxDistribution.put(year, max);
    }
    maxDistribution = maxDistribution.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    return maxDistribution;
  }

  @Override
  public Map<Integer, Integer> getDistributionOfNumberOfAnswers() {
    Map<Integer, Integer> answerEveryYear = new HashMap<>();
    List<Answer> answers = stackOverflowThreadMapper.getAllAnswers();
    for (Answer answer : answers) {
      long time = answer.getCreationDate();
      LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault());
      // 获取年份
      int year = dateTime.getYear();
      answerEveryYear.put(year, answerEveryYear.getOrDefault(year, 0) + 1);
      answerEveryYear = answerEveryYear.entrySet()
          .stream()
          .sorted(Map.Entry.comparingByKey())
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
              (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
    return answerEveryYear;
  }

  @Override
  public String getPercentageOfQuestionsWithAcceptedAnswers() {
    List<Integer> questionIds = stackOverflowThreadMapper.getAllQuestionIds();
    double acceptedAnswerCount = 0;
    for (Integer questionId : questionIds) {
      List<Answer> answers = stackOverflowThreadMapper.getAnswersByQuestionId(questionId);
      for (Answer answer : answers) {
        if (answer.isAccepted()) {
          acceptedAnswerCount++;//one question could only have one accepted answer
        }
      }
    }
    double other = questionIds.size() - acceptedAnswerCount;
    return acceptedAnswerCount + " " + other;

  }

  @Override
  public Map<Integer, Integer> getDistributionOfQuestionResolutionTime() {
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
    //划分为10个区间
    Map<Integer, Integer> questionResolutionTime2 = new HashMap<>();
    for (long time : questionResolutionTime.keySet()) {
      boolean flag = false;
      for (int i = 1; i < 50; i++) {
        if (time < i * 200) {
          questionResolutionTime2.put(i * 200, questionResolutionTime2.getOrDefault(i * 200, 0) + questionResolutionTime.get(time));
          flag = true;
          break;
        }
      }
      if (!flag) {
        questionResolutionTime2.put(10000, questionResolutionTime2.getOrDefault(10000, 0) + questionResolutionTime.get(time));
      }
    }
    //将map按照key排序
    questionResolutionTime2 = questionResolutionTime2.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    return questionResolutionTime2;
  }


  @Override
  public String getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes() {
    List<Integer> questionIds = stackOverflowThreadMapper.getAllQuestionIds();
    double count = 0;
    for (int questionId : questionIds) {
      int acceptedAnswerUpvote = 0;
      int otherAnswerMaxUpvote = 0;
      List<Answer> answers = stackOverflowThreadMapper.getAnswersByQuestionId(questionId);
      for (Answer answer : answers) {
        if (answer.isAccepted()) {
          acceptedAnswerUpvote = answer.getUpVoteCount();
        } else {
          otherAnswerMaxUpvote = Math.max(otherAnswerMaxUpvote, answer.getUpVoteCount());
        }
      }
      if (acceptedAnswerUpvote != 0 && acceptedAnswerUpvote < otherAnswerMaxUpvote) {
        count++;
      }
    }
    return count + " " + (questionIds.size() - count);
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
  public List<String> getDistributionOfUserParticipation() {
    List<Integer> questionIds = stackOverflowThreadMapper.getAllQuestionIds();
    List<String> threadParticipationCount = new ArrayList<>();

    for (Integer questionId : questionIds) {
      List<String> participants = stackOverflowThreadMapper.getParticipantsByQuestionId(questionId);
      List<Answer> answers = stackOverflowThreadMapper.getAnswersByQuestionId(questionId);
      List<Comment> comments = stackOverflowThreadMapper.getCommentsByQuestionId(questionId);
      List<String> answersParticipants = new ArrayList<>();
      for (Answer answer : answers) {
        answersParticipants.addAll(stackOverflowThreadMapper.getParticipantsByAnswerId(answer.getAnswerId()));
      }
      answersParticipants = new ArrayList<>(new HashSet<>(answersParticipants));
      List<String> commentParticipants = new ArrayList<>();
      for (Comment comment : comments) {
        commentParticipants.addAll(stackOverflowThreadMapper.getParticipantsByCommentId(comment.getCommentId()));
      }
      commentParticipants = new ArrayList<>(new HashSet<>(commentParticipants));
      //把两者放进一个list里面，然后去重
        participants.addAll(answersParticipants);
        participants.addAll(commentParticipants);
        participants = new ArrayList<>(new HashSet<>(participants));
      threadParticipationCount.add(participants.size() + " " + answersParticipants.size() + " " + commentParticipants.size());
    }

    return threadParticipationCount;
  }


  @Override
  public List<String> getMostActiveUsers() {
    int limit = 7; // 设定返回的最活跃用户数量，可以根据需要调整
    List<Map<String, Object>> mostActiveUsersData = stackOverflowThreadMapper.getMostActiveUsersWithLimit(limit);
    List<String> mostActiveUsers = new ArrayList<>();

    for (Map<String, Object> userData : mostActiveUsersData) {
      String s = stackOverflowThreadMapper.getDisplayNameByOwnerId((String) userData.get("owner_id"));
      if (s != null) {
        mostActiveUsers.add(s);
      }
    }
    return mostActiveUsers;
  }

}