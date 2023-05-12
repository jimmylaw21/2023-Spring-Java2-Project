package cse.java2.project.service.impl;
import cse.java2.project.domain.model.dto.Answer;
import cse.java2.project.domain.model.dto.Comment;
import cse.java2.project.domain.model.dto.Owner;
import cse.java2.project.domain.model.dto.StackOverflowThread;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//添加@Service或@Component注解，以便Spring能够自动检测到它，并将其实例化并添加到容器中
//@Service
//public class ThreadCollectorApp {
//
//  public static void main(String[] args) {
//    ThreadCollector threadCollector = new ThreadCollector();
//    String tag = "java";
//    int pageSize = 1;
//    int pageNum = 10;
//    try {
//      List<StackOverflowThread> stackOverflowThreads = new ArrayList<>();
////        for (int i = 1; i <= pageNum; i++) {
//            stackOverflowThreads.addAll(threadCollector.getStackOverflowThreadsByTag(tag, pageSize, pageNum));
////        }
//      String resource = "mybatis-config.xml";
//      InputStream inputStream = Resources.getResourceAsStream(resource);
//      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//      SqlSession sqlSession = sqlSessionFactory.openSession();
//      try {
//        StackOverflowThreadMapper mapper = sqlSession.getMapper(StackOverflowThreadMapper.class);
//        //插入用户数据
//        // 插入用户数据
//        Set<Owner> users = new LinkedHashSet<>();
//        for (StackOverflowThread thread : stackOverflowThreads) {
//          users.add(thread.getQuestion().getOwner());
//          for (Answer answer : thread.getAnswers()) {
//            users.add(answer.getOwner());
//          }
//          for (Comment comment : thread.getComments()) {
//            users.add(comment.getOwner());
//          }
//        }
//
//        for (Owner owner : users) {
//          if (owner.getUserId() != null) {
//            try {
//              mapper.insertOwner(owner);
//            } catch (DuplicateKeyException e) {
//              System.err.println("Duplicate key found for owner: " + owner.getUserId() + ". Skipping insert.");
//            }
//          }
//        }
//
////插入问题数据
//        for (StackOverflowThread thread : stackOverflowThreads) {
//          try {
//            mapper.insertQuestion(thread.getQuestion()); // 调用Mapper中定义的add方法插入数据
//          } catch (DuplicateKeyException e) {
//            System.err.println("Duplicate key found for question: " + thread.getQuestion().getQuestionId() + ". Skipping insert.");
//          }
//          //插入回答数据
//          for (Answer answer : thread.getAnswers()) {
//            try {
//              mapper.insertAnswer(answer);
//            } catch (DuplicateKeyException e) {
//              System.err.println("Duplicate key found for answer: " + answer.getAnswerId() + ". Skipping insert.");
//            }
//          }
//          //插入评论数据
//          for (Comment comment : thread.getComments()) {
//            try {
//              mapper.insertComment(comment, thread.getQuestion().getQuestionId());
//            } catch (DuplicateKeyException e) {
//              System.err.println("Duplicate key found for comment: " + comment.getCommentId() + ". Skipping insert.");
//            }
//          }
//        }
//
//        sqlSession.commit(); // 提交事务
//      } finally {
//        sqlSession.close(); // 关闭SqlSession
//      }
//
//    } catch (IOException e) {
//      System.err.println("Error retrieving Stack Overflow threads: " + e.getMessage());
//    }
//  }
//}

@Service
public class ThreadCollectorApp {

  public static void main(String[] args) {
    ThreadCollector threadCollector = new ThreadCollector();
    String tag = "java";
    int pageSize = 1;
    int pageNum = 10;
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    try {
      inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      StackOverflowThreadMapper mapper = sqlSession.getMapper(StackOverflowThreadMapper.class);

      int successCount = 1;
      while (successCount <= pageNum) {
        try {
          List<StackOverflowThread> stackOverflowThreads = threadCollector.getStackOverflowThreadsByTag(tag, pageSize, successCount);
          processData(stackOverflowThreads, mapper);
          sqlSession.commit(); // 提交事务
        } catch (IOException e) {
          System.err.println("Error retrieving Stack Overflow threads: " + e.getMessage());
          // Retry or continue with the next iteration based on your requirements.
            continue;
        }
        successCount++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void processData(List<StackOverflowThread> stackOverflowThreads, StackOverflowThreadMapper mapper) {
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
      if (owner.getUserId() != null) {
        try {
          mapper.insertOwner(owner);
        } catch (Exception e) {
          System.err.println("Duplicate key found for owner: " + owner.getUserId() + ". Skipping insert.");
        }
      }
    }

    for (StackOverflowThread thread : stackOverflowThreads) {
      try {
        mapper.insertQuestion(thread.getQuestion());
      } catch (Exception e) {
        System.err.println("Duplicate key found for question: " + thread.getQuestion().getQuestionId() + ". Skipping insert.");
      }
      for (Answer answer : thread.getAnswers()) {
        try {
          mapper.insertAnswer(answer);
        } catch (Exception e) {
          System.err.println("Duplicate key found for answer: " + answer.getAnswerId() + ". Skipping insert.");
        }
      }
      for (Comment comment : thread.getComments()) {
        try {
          mapper.insertComment(comment, thread.getQuestion().getQuestionId());
        } catch (Exception e) {
          System.err.println("Duplicate key found for comment: " + comment.getCommentId() + ". Skipping insert.");
        }
      }
    }
  }

}
