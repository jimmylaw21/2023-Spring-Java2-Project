package cse.java2.project.service;

import cse.java2.project.domain.model.dto.Answer;
import cse.java2.project.domain.model.dto.Comment;
import cse.java2.project.domain.model.dto.Question;
import cse.java2.project.domain.model.dto.StackOverflowThread;

import java.io.IOException;
import java.util.List;

public class ThreadCollectorApp {

  public static void main(String[] args) {
    ThreadCollector threadCollector = new ThreadCollector();
    String tag = "java";
    int pageSize = 5;
    int pageNum = 1;

    try {
      List<StackOverflowThread> stackOverflowThreads = threadCollector.getStackOverflowThreadsByTag(tag, pageSize, pageNum);

      System.out.println("Top " + pageSize + " Stack Overflow threads with the \"" + tag + "\" tag:");
      int threadCounter = 1;
      for (StackOverflowThread stackOverflowThread : stackOverflowThreads) {
        System.out.println(threadCounter + ". Question: " + stackOverflowThread.getQuestion().getTitle());
        System.out.println("Answers:");
        for (Answer answer : stackOverflowThread.getAnswers()) {
          System.out.println(" - Answer ID: " + answer.getAnswerId());
        }
        System.out.println("Question Comments:");
        for (Comment comment : stackOverflowThread.getComments()) {
          System.out.println(" - Comment ID: " + comment.getCommentId());
        }
        System.out.println();
        threadCounter++;
      }
    } catch (IOException e) {
      System.err.println("Error retrieving Stack Overflow threads: " + e.getMessage());
    }
  }
}
