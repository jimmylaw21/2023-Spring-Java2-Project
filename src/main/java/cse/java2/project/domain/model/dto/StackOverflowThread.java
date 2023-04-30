package cse.java2.project.domain.model.dto;

import java.util.List;

public class StackOverflowThread {
  private Question question;
  private List<Answer> answers;
  private List<Comment> comments;

  public StackOverflowThread(Question question, List<Answer> answers, List<Comment> comments) {
    this.question = question;
    this.answers = answers;
    this.comments = comments;
  }

  public StackOverflowThread(){
    this.question = null;
    this.answers = null;
    this.comments = null;
  }

  public Question getQuestion() {
    return question;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "Question: " + question + "\nAnswers: " + answers + "\nComments: " + comments;
  }
}
