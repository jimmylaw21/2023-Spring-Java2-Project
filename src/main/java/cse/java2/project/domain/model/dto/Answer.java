package cse.java2.project.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Answer implements Serializable {
  @JsonProperty("owner")
  private Owner owner;

  @JsonProperty("down_vote_count")
  private int downVoteCount;

  @JsonProperty("up_vote_count")
  private int upVoteCount;

  @JsonProperty("is_accepted")
  private boolean isAccepted;

  @JsonProperty("score")
  private int score;

  @JsonProperty("last_activity_date")
  private long lastActivityDate;

  @JsonProperty("last_edit_date")
  private long lastEditDate;

  @JsonProperty("creation_date")
  private long creationDate;

  @JsonProperty("answer_id")
  private int answerId;

  @JsonProperty("question_id")
  private int questionId;

  @JsonProperty("link")
  private String link;

  @JsonProperty("title")
  private String title;

  @JsonProperty("body_markdown")
  private String body;

  // Getters and setters for all fields

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public int getDownVoteCount() {
    return downVoteCount;
  }

  public void setDownVoteCount(int downVoteCount) {
    this.downVoteCount = downVoteCount;
  }

  public int getUpVoteCount() {
    return upVoteCount;
  }

  public void setUpVoteCount(int upVoteCount) {
    this.upVoteCount = upVoteCount;
  }

  public boolean isAccepted() {
    return isAccepted;
  }

  public void setAccepted(boolean accepted) {
    isAccepted = accepted;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public long getLastActivityDate() {
    return lastActivityDate;
  }

  public void setLastActivityDate(long lastActivityDate) {
    this.lastActivityDate = lastActivityDate;
  }

  public long getLastEditDate() {
    return lastEditDate;
  }

  public void setLastEditDate(long lastEditDate) {
    this.lastEditDate = lastEditDate;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(long creationDate) {
    this.creationDate = creationDate;
  }

  public int getAnswerId() {
    return answerId;
  }

  public void setAnswerId(int answerId) {
    this.answerId = answerId;
  }

  public int getQuestionId() {
    return questionId;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  //比较两个Answer对象的awswerId是否相等
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (obj instanceof Answer) {
      Answer answer = (Answer) obj;
      return this.answerId == answer.answerId;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return answerId;
  }


  @Override
  public String toString() {
    return "Answer{" +
        "owner=" + owner +
        ", downVoteCount=" + downVoteCount +
        ", upVoteCount=" + upVoteCount +
        ", isAccepted=" + isAccepted +
        ", score=" + score +
        ", lastActivityDate=" + lastActivityDate +
        ", lastEditDate=" + lastEditDate +
        ", creationDate=" + creationDate +
        ", answerId=" + answerId +
        ", questionId=" + questionId +
        ", link='" + link + '\'' +
        ", title='" + title + '\'' +
        ", body='" + body + '\'' +
        '}';
  }
}
