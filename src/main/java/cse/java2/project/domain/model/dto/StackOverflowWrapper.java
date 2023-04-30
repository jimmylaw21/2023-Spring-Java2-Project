package cse.java2.project.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StackOverflowWrapper<T> {

  @JsonProperty("backoff")
  private Integer backoff;

  @JsonProperty("error_id")
  private Integer errorId;

  @JsonProperty("error_message")
  private String errorMessage;

  @JsonProperty("error_name")
  private String errorName;

  @JsonProperty("has_more")
  private boolean hasMore;

  @JsonProperty("items")
  private List<T> items;

  @JsonProperty("page")
  private Integer page;

  @JsonProperty("page_size")
  private Integer pageSize;

  @JsonProperty("quota_max")
  private Integer quotaMax;

  @JsonProperty("quota_remaining")
  private Integer quotaRemaining;

  @JsonProperty("total")
  private Integer total;

  @JsonProperty("type")
  private String type;

  // Add getters and setters for all fields

  public Integer getBackoff() {
    return backoff;
  }

  public void setBackoff(Integer backoff) {
    this.backoff = backoff;
  }

  public Integer getErrorId() {
    return errorId;
  }

  public void setErrorId(Integer errorId) {
    this.errorId = errorId;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorName() {
    return errorName;
  }

  public void setErrorName(String errorName) {
    this.errorName = errorName;
  }

  public boolean isHasMore() {
    return hasMore;
  }

  public void setHasMore(boolean hasMore) {
    this.hasMore = hasMore;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getQuotaMax() {
    return quotaMax;
  }

  public void setQuotaMax(Integer quotaMax) {
    this.quotaMax = quotaMax;
  }

  public Integer getQuotaRemaining() {
    return quotaRemaining;
  }

  public void setQuotaRemaining(Integer quotaRemaining) {
    this.quotaRemaining = quotaRemaining;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}