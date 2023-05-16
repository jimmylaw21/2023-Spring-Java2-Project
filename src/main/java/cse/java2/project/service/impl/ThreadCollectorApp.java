package cse.java2.project.service.impl;
import cse.java2.project.domain.model.dto.Answer;
import cse.java2.project.domain.model.dto.Comment;
import cse.java2.project.domain.model.dto.Owner;
import cse.java2.project.domain.model.dto.StackOverflowThread;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//添加@Service或@Component注解，以便Spring能够自动检测到它，并将其实例化并添加到容器中
@Transactional(rollbackFor = Exception.class)
@Service
public class ThreadCollectorApp {

  public static void main(String[] args) {
    ThreadCollector threadCollector = new ThreadCollector();
    String tag = "java";
    int pageSize = 10;
    int pageNumStart = 2;
    int pageNumEnd = 2;
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    try {
      inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      StackOverflowThreadMapper mapper = sqlSession.getMapper(StackOverflowThreadMapper.class);

      int successCount = pageNumStart;
      List<StackOverflowThread> stackOverflowThreads = new ArrayList<>();

      while (successCount <= pageNumEnd) {
        try {
          stackOverflowThreads.addAll(threadCollector.getStackOverflowThreadsByTag(tag, pageSize, successCount));
        } catch (IOException e) {
          System.err.println("Error retrieving Stack Overflow threads: " + e.getMessage());
          // Retry or continue with the next iteration based on your requirements.
            continue;
        }
        successCount++;
      }
      processData(stackOverflowThreads, mapper);
      sqlSession.commit(); // 提交事务
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void processData(List<StackOverflowThread> stackOverflowThreads, StackOverflowThreadMapper mapper) {
    List<Integer> ownerIds = mapper.getAllOwnerIds();
    List<Integer> questionIds = mapper.getAllQuestionIds();
    List<Integer> answerIds = mapper.getAllAnswerIds();
    List<Integer> commentIds = mapper.getAllCommentIds();

    Set<Owner> users = new LinkedHashSet<>();
    for (StackOverflowThread thread : stackOverflowThreads) {
      users.add(thread.getQuestion().getOwner());
      for (Answer answer : thread.getAnswers()) {
        users.add(answer.getOwner());
      }
      for (Comment comment : thread.getComments()) {
        users.add(comment.getOwner());
      }
    }

    for (Owner owner : users) {
      if (owner.getUserId() != null && !ownerIds.contains(owner.getUserId())) {
        try {
          mapper.insertOwner(owner);
        } catch (PersistenceException e) {
          Throwable cause = e.getCause();
          if (cause instanceof PSQLException) {
            System.err.println("Owner Duplicate key found. Skipping insert. Owner ID: " + owner.getUserId());
          } else {
            // Handle generic persistence exceptions
            e.printStackTrace();
          }
        }
      }
    }

    for (StackOverflowThread thread : stackOverflowThreads) {
      if (thread.getQuestion().getQuestionId() != 0 && !questionIds.contains(thread.getQuestion().getQuestionId())) {
        try {
          mapper.insertQuestion(thread.getQuestion());
        } catch (PersistenceException e) {
          Throwable cause = e.getCause();
          if (cause instanceof PSQLException) {
            System.err.println("Question Duplicate key found. Skipping insert. Question ID: " + thread.getQuestion().getQuestionId());
          } else {
            // Handle generic persistence exceptions
            e.printStackTrace();
          }
        }
      }

      for (Answer answer : thread.getAnswers()) {
        if (answer.getAnswerId() != 0 && !answerIds.contains(answer.getAnswerId())) {
          try {
            mapper.insertAnswer(answer);
          } catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof PSQLException) {
              System.err.println("Answer Duplicate key found. Skipping insert. Answer ID: " + answer.getAnswerId());
            } else {
              // Handle generic persistence exceptions
              e.printStackTrace();
            }
          }
        }
      }
      for (Comment comment : thread.getComments()) {
        if (comment.getCommentId() != 0 && !commentIds.contains(comment.getCommentId())) {
          try {
            mapper.insertComment(comment, thread.getQuestion().getQuestionId());
          } catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause instanceof PSQLException) {
              System.err.println("Duplicate key found. Skipping insert. Comment ID: " + comment.getCommentId());
            } else {
              // Handle generic persistence exceptions
              e.printStackTrace();
            }
          }
        }
      }
    }
  }
}
