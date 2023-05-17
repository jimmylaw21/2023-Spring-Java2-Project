package cse.java2.project.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Question implements Serializable {

//  public Question() {
//    // 初始化 tags 列表
//    this.tags = new ArrayList<>();
//  }

  @JsonProperty("tags")
  private List<String> tags;

  @JsonProperty("owner")
  private Owner owner;

  @JsonProperty("is_answered")
  private boolean isAnswered;

  @JsonProperty("view_count")
  private int viewCount;

  @JsonProperty("favorite_count")
  private int favoriteCount;

  @JsonProperty("down_vote_count")
  private int downVoteCount;

  @JsonProperty("up_vote_count")
  private int upVoteCount;

//  @JsonProperty("accepted_answer_id")
//  private int acceptedAnswerId;

  @JsonProperty("answer_count")
  private int answerCount;

  @JsonProperty("score")
  private int score;

  @JsonProperty("last_activity_date")
  private long lastActivityDate;

  @JsonProperty("creation_date")
  private long creationDate;

  @JsonProperty("last_edit_date")
  private long lastEditDate;

  @JsonProperty("question_id")
  private int questionId;

  @JsonProperty("link")
  private String link;

  @JsonProperty("title")
  private String title;

  @JsonProperty("body_markdown")
  private String body;

  // Getters and setters for all fields

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public boolean isAnswered() {
    return isAnswered;
  }

  public void setAnswered(boolean answered) {
    isAnswered = answered;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getFavoriteCount() {
    return favoriteCount;
  }

  public void setFavoriteCount(int favoriteCount) {
    this.favoriteCount = favoriteCount;
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

//  public int getAcceptedAnswerId() {
//    return acceptedAnswerId;
//  }
//
//  public void setAcceptedAnswerId(int acceptedAnswerId) {
//    this.acceptedAnswerId = acceptedAnswerId;
//  }

  public int getAnswerCount() {
    return answerCount;
  }

  public void setAnswerCount(int answerCount) {
    this.answerCount = answerCount;
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

  public long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(long creationDate) {
    this.creationDate = creationDate;
  }

  public long getLastEditDate() {
    return lastEditDate;
  }

  public void setLastEditDate(long lastEditDate) {
    this.lastEditDate = lastEditDate;
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

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (obj instanceof Question) {
      Question question = (Question) obj;
      return this.questionId == question.questionId;
    }
    return false;
  }

  @Override
    public int hashCode() {
        return this.questionId;
    }

  @Override
  public String toString() {
    return "Question{" +
            "tags=" + tags +
            ", owner=" + owner +
            ", isAnswered=" + isAnswered +
            ", viewCount=" + viewCount +
            ", favoriteCount=" + favoriteCount +
            ", downVoteCount=" + downVoteCount +
            ", upVoteCount=" + upVoteCount +
            ", answerCount=" + answerCount +
            ", score=" + score +
            ", lastActivityDate=" + lastActivityDate +
            ", creationDate=" + creationDate +
            ", lastEditDate=" + lastEditDate +
            ", questionId=" + questionId +
            ", link='" + link + '\'' +
            ", title='" + title + '\'' +
            ", body='" + body + '\'' +
            '}';
  }
}
