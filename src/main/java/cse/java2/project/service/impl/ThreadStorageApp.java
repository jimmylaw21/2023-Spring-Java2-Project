package cse.java2.project.service.impl;

import cse.java2.project.domain.model.dto.*;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import static cse.java2.project.utils.IOUtil.readDataFromFile;
import static cse.java2.project.utils.IOUtil.writeDataToFile;

@Service
public class ThreadStorageApp {

  static String resource = "mybatis-config.xml";
  static InputStream inputStream;

  static {
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  static SqlSession sqlSession = sqlSessionFactory.openSession();
  static StackOverflowThreadMapper mapper = sqlSession.getMapper(StackOverflowThreadMapper.class);
  static String[] fileName = {"owners.ser", "comments.ser", "answers.ser", "questions.ser"};

  @Autowired
  public ThreadStorageApp() throws IOException {
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    deserializeData(fileName);
  }

  private static void deserializeData(String[] fileName) throws IOException, ClassNotFoundException {

    long startTime = System.currentTimeMillis();

    Set<Owner> owners = readDataFromFile(fileName[0]);
    Set<Comment> comments = readDataFromFile(fileName[1]);
    Set<Answer> answers = readDataFromFile(fileName[2]);
    Set<Question> questions = readDataFromFile(fileName[3]);

    try {
      processData(owners, comments, answers, questions);
      sqlSession.commit(); // 提交事务
    } catch (Exception e) {
      if (sqlSession != null) {
        sqlSession.rollback();  // 如果出现异常，回滚事务
      }
      System.err.println("Error processing data: " + e.getMessage());
    } finally {
      if (sqlSession != null) {
        sqlSession.close();  // 最后，总是关闭连接
      }
    }
//        //打印owner的信息
//        for (Owner owner : owners) {
//            System.out.println(owner);
//        }

    long endTime = System.currentTimeMillis();

    long duration = endTime - startTime;

    long seconds = (duration / 1000) % 60;
    long minutes = (duration / (1000 * 60)) % 60;
    long hours = (duration / (1000 * 60 * 60)) % 24;

    System.out.printf("Execution time: %02d:%02d:%02d\n", hours, minutes, seconds);

    System.out.println("owners size: " + owners.size());
    System.out.println("comments size: " + comments.size());
    System.out.println("answers size: " + answers.size());
    System.out.println("questions size: " + questions.size());

  }

  private static void processData(Set<Owner> owners, Set<Comment> comments, Set<Answer> answers
      , Set<Question> questions) {
    for (Owner owner : owners) {
      try {
        if (owner.getUserId() != null) {
          mapper.insertOwner(owner);
        }
      } catch (PersistenceException e) {
        e.printStackTrace();
      }
    }

    for (Question question : questions) {
      try {
        mapper.insertQuestion(question);
      } catch (PersistenceException e) {
        e.printStackTrace();
      }
    }

    for (Answer answer : answers) {
      try {
        mapper.insertAnswer(answer);
      } catch (PersistenceException e) {
        e.printStackTrace();
      }
    }

    for (Comment comment : comments) {
      try {
        mapper.insertComment(comment, comment.getQuestionId());
      } catch (PersistenceException e) {
        e.printStackTrace();
      }
    }
  }
}
