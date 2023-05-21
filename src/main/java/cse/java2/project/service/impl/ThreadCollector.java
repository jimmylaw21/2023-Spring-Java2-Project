package cse.java2.project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cse.java2.project.filter.fliter;
import cse.java2.project.domain.model.dto.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ThreadCollector {

  private static final String API_BASE_URL = "https://api.stackexchange.com/2.3/";
  private static final String CLIENT_ID = "26107";
  private static final String KEY = "qeUQ3LQJnDuzdfxTekiPSg((";
  private RestTemplate restTemplate;
  private ObjectMapper objectMapper;

  public ThreadCollector() {
    ClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());

    this.restTemplate = new RestTemplate(requestFactory);
    restTemplate.setInterceptors(Collections.singletonList((ClientHttpRequestInterceptor) (request, body, execution) -> {
      request.getHeaders().add("Accept-Encoding", "gzip");
      return execution.execute(request, body);
    }));

    this.objectMapper = new ObjectMapper();
  }

  public List<StackOverflowThread> getStackOverflowThreadsByTag(String tag, int pageSize, int pageNum) throws IOException {
    List<Question> questions = getStackOverflowQuestions(tag, pageSize, pageNum);
    List<StackOverflowThread> stackOverflowThreads = new ArrayList<>();

    for (Question question : questions) {
      StackOverflowThread stackOverflowThread = getStackOverflowThread(question);
      stackOverflowThreads.add(stackOverflowThread);
    }

    return stackOverflowThreads;
  }

  public StackOverflowThread getStackOverflowThread(Question question) throws IOException {
    StackOverflowThread stackOverflowThread = new StackOverflowThread();
    stackOverflowThread.setQuestion(question);

    List<Answer> answers = getAnswerByQuestion(question.getQuestionId());
    List<Comment> comments = getCommentByQuestion(question.getQuestionId());
    for (Answer answer : answers) {
      comments.addAll(getCommentByAnswer(answer.getAnswerId()));
      // do something with answerComments, e.g., add them to the answer object
    }
    stackOverflowThread.setAnswers(answers);

    stackOverflowThread.setComments(comments);

    return stackOverflowThread;
  }


  public List<Question> getStackOverflowQuestions(String tag, int pageSize, int pageNum) throws IOException {
    String queryUrl = API_BASE_URL + "questions?page=" + pageNum + "&pagesize=" + pageSize
        + "&order=desc&sort=votes&tagged=" + tag + "&site=stackoverflow&client_id=" + CLIENT_ID
        + "&key=" + KEY + "&filter=" + fliter.fliter;

    String responseJson = fetchContentFromUrl(queryUrl);
    System.out.println(responseJson);
    StackOverflowWrapper<Question> stackOverflowWrapper = objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructParametricType(StackOverflowWrapper.class, Question.class));

    return stackOverflowWrapper.getItems();
  }

  public List<Answer> getAnswerByQuestion(int questionId) throws IOException {
    List<Answer> allAnswers = new ArrayList<>();
    int page = 1;
    while (true) {
      String answerUrl = API_BASE_URL + "questions/" + questionId +
              "/answers?order=desc&sort=votes&site=stackoverflow&client_id=" + CLIENT_ID +
              "&key=" + KEY +
              "&filter=" +
              fliter.fliter +
              "&page=" + page +
              "&pagesize=100";
      String answerResponseJson = fetchContentFromUrl(answerUrl);
      StackOverflowWrapper<Answer> answerWrapper = objectMapper.readValue(answerResponseJson, objectMapper.getTypeFactory().constructParametricType(StackOverflowWrapper.class, Answer.class));
      List<Answer> answers = answerWrapper.getItems();
      if (answers.isEmpty()) {
        break;
      }
      allAnswers.addAll(answers);
      page++;
    }
    return allAnswers;
  }


  public List<Comment> getCommentByQuestion(int questionId) throws IOException {
    List<Comment> allComments = new ArrayList<>();
    int page = 1;
    while (true) {
      String commentUrl = API_BASE_URL + "questions/" + questionId +
              "/comments?order=desc&sort=votes&site=stackoverflow&client_id=" + CLIENT_ID +
              "&key=" + KEY +
              "&filter=" +
                fliter.fliter +
              "&page=" + page +
              "&pagesize=100";
      String commentResponseJson = fetchContentFromUrl(commentUrl);
      StackOverflowWrapper<Comment> commentWrapper = objectMapper.readValue(commentResponseJson, objectMapper.getTypeFactory().constructParametricType(StackOverflowWrapper.class, Comment.class));
      List<Comment> comments = commentWrapper.getItems();
      if (comments.isEmpty()) {
        break;
      }
      allComments.addAll(comments);
      page++;
    }
    return allComments;
  }

  public List<Comment> getCommentByAnswer(int answerId) throws IOException {
    List<Comment> allComments = new ArrayList<>();
    int page = 1;
    while (true) {
      String commentUrl = API_BASE_URL + "answers/" + answerId +
              "/comments?order=desc&sort=votes&site=stackoverflow&client_id=" + CLIENT_ID +
              "&key=" + KEY +
              "&filter=" +
                fliter.fliter +
              "&page=" + page +
              "&pagesize=100";
      String commentResponseJson = fetchContentFromUrl(commentUrl);
      StackOverflowWrapper<Comment> commentWrapper = objectMapper.readValue(commentResponseJson, objectMapper.getTypeFactory().constructParametricType(StackOverflowWrapper.class, Comment.class));
      List<Comment> comments = commentWrapper.getItems();
      if (comments.isEmpty()) {
        break;
      }
      allComments.addAll(comments);
      page++;
    }
    return allComments;
  }



  private String fetchContentFromUrl(String url) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet request = new HttpGet(url);
    request.addHeader("Accept-Encoding", "gzip");
    try (CloseableHttpResponse response = httpClient.execute(request)) {
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        return EntityUtils.toString(entity, StandardCharsets.UTF_8);
      } else {
        throw new IOException("No content in the response");
      }
    }
  }

}
