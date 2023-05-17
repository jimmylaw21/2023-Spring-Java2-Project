package cse.java2.project.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment implements Serializable {
  @JsonProperty("owner")
  private Owner owner;

  @JsonProperty("edited")
  private boolean edited;

  @JsonProperty("creation_date")
  private long creationDate;

  @JsonProperty("post_id")
  private int postId;

  @JsonProperty("comment_id")
  private int commentId;

  @JsonProperty("link")
  private String link;

  @JsonProperty("body")
  private String body;

  private int questionId;
  // Getters and setters for all fields
  public int getQuestionId() {
    return questionId;
  }
  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }
  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public boolean isEdited() {
    return edited;
  }

  public void setEdited(boolean edited) {
    this.edited = edited;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(long creationDate) {
    this.creationDate = creationDate;
  }

  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
  }

  public int getCommentId() {
    return commentId;
  }

  public void setCommentId(int commentId) {
    this.commentId = commentId;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  //比较commentId来判断是否相等
  @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Comment) {
            Comment comment = (Comment) obj;
            return this.commentId == comment.commentId;
        }
        return false;
    }

  @Override
  public int hashCode() {
    return commentId;
  }


  @Override
    public String toString() {
        return "Comment{" +
                "owner=" + owner +
                ", edited=" + edited +
                ", creationDate=" + creationDate +
                ", postId=" + postId +
                ", commentId=" + commentId +
                ", link='" + link + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
