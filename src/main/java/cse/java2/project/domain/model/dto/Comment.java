package cse.java2.project.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
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

  // Getters and setters for all fields

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
