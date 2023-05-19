package cse.java2.project.service.impl;
import cse.java2.project.domain.model.dto.*;
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

import static cse.java2.project.utils.IOUtil.readDataFromFile;
import static cse.java2.project.utils.IOUtil.writeDataToFile;

//添加@Service或@Component注解，以便Spring能够自动检测到它，并将其实例化并添加到容器中
@Service
public class ThreadCollectorApp {

  static String[] fileName = {"owners.ser", "comments.ser", "answers.ser", "questions.ser"};

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    serializeData(fileName);
  }

  private static void serializeData(String[] fileName){

    long startTime = System.currentTimeMillis();

    ThreadCollector threadCollector = new ThreadCollector();
    String tag = "java";
    int pageSize = 10;
    int pageNumStart = 46;
    int pageNumEnd = 50;

    Set<Owner> owners = readDataFromFile(fileName[0]);
    Set<Comment> comments = readDataFromFile(fileName[1]);
    Set<Answer> answers = readDataFromFile(fileName[2]);
    Set<Question> questions = readDataFromFile(fileName[3]);

    List<StackOverflowThread> stackOverflowThreads;
    int successCount = pageNumStart;
    while (successCount <= pageNumEnd) {
      try {
        stackOverflowThreads = threadCollector.getStackOverflowThreadsByTag(tag, pageSize, successCount);
        for (StackOverflowThread thread : stackOverflowThreads) {

          if (thread.getQuestion().getOwner() != null){
            owners.add(thread.getQuestion().getOwner());
          }

          for (Answer answer : thread.getAnswers()) {
            if (answer.getOwner() != null) {
              owners.add(answer.getOwner());
            }
          }
          for (Comment comment : thread.getComments()) {
            comment.setQuestionId(thread.getQuestion().getQuestionId());
            if (comment.getOwner() != null) {
              owners.add(comment.getOwner());
            }
          }
          comments.addAll(thread.getComments());
          answers.addAll(thread.getAnswers());
          questions.add(thread.getQuestion());
        }
      } catch (IOException e) {
        System.err.println("Error retrieving Stack Overflow threads: " + e.getMessage());
        // Retry or continue with the next iteration based on your requirements.
        continue;
      }
      successCount++;
    }

    writeDataToFile(fileName[0], owners);
    writeDataToFile(fileName[1], comments);
    writeDataToFile(fileName[2], answers);
    writeDataToFile(fileName[3], questions);

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

}
