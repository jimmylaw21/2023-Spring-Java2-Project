package cse.java2.project.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner implements Serializable {
  @JsonProperty("reputation")
  private int reputation;

  @JsonProperty("user_id")
  private String userId;

  @JsonProperty("user_type")
  private String userType;

  @JsonProperty("accept_rate")
  private int acceptRate;

  @JsonProperty("profile_image")
  private String profileImage;

  @JsonProperty("display_name")
  private String displayName;

  @JsonProperty("link")
  private String link;

  // Getters and setters for all fields

  public int getReputation() {
    return reputation;
  }

  public void setReputation(int reputation) {
    this.reputation = reputation;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public int getAcceptRate() {
    return acceptRate;
  }

  public void setAcceptRate(int acceptRate) {
    this.acceptRate = acceptRate;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o){
      return true;
    }
    if (o == null || getClass() != o.getClass()){
      return false;
    }
    Owner owner = (Owner) o;
    return Objects.equals(userId, owner.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

    @Override
    public String toString() {
        return "Owner{" +
                "reputation=" + reputation +
                ", userId='" + userId + '\'' +
                ", userType='" + userType + '\'' +
                ", acceptRate=" + acceptRate +
                ", profileImage='" + profileImage + '\'' +
                ", displayName='" + displayName + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
