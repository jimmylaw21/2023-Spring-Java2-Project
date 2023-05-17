package cse.java2.project;

import cse.java2.project.service.impl.DataAnalyzer;
import cse.java2.project.service.impl.JavaApiIdentifier;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import cse.java2.project.domain.model.dto.Question;
import cse.java2.project.mapper.StackOverflowThreadMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DataAnalyzerTests {

    @Autowired
    private DataAnalyzer dataAnalyzer;

    @Autowired
    private JavaApiIdentifier javaApiIdentifier;

    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    StackOverflowThreadMapper mapper = sqlSession.getMapper(StackOverflowThreadMapper.class);

    public DataAnalyzerTests() throws IOException {
    }

    @BeforeEach
    public void setUp() throws IOException {
        dataAnalyzer = new DataAnalyzer(mapper);
        javaApiIdentifier = new JavaApiIdentifier(mapper);
//        Question q1 = new Question();
//        q1.setAnswerCount(3);
//
//        Question q2 = new Question();
//        q2.setAnswerCount(0);
//
//        List<Question> allQuestions = Arrays.asList(q1, q2);
//
//        when(stackOverflowThreadMapper.getAllQuestions()).thenReturn(allQuestions);
    }

    @Test
    public void testGetPercentageOfQuestionsWithoutAnswers() {
        String result = dataAnalyzer.getPercentageOfQuestionsWithoutAnswers();
        System.out.println(result);
    }

    @Test
    public void testGetAverageNumberOfAnswers() {
        double result = dataAnalyzer.getAverageNumberOfAnswers();
        System.out.println(result);
    }

    @Test
    public void testGetMaximumNumberOfAnswers() {
        int result = dataAnalyzer.getMaximumNumberOfAnswers();
        System.out.println(result);
    }

    @Test
    public void testGetDistributionOfAnswers() {
        Map<Integer, Integer> result = dataAnalyzer.getDistributionOfNumberOfAnswers();
        System.out.println(result);
    }

    @Test
    public void testGetPercentageOfQuestionsWithAcceptedAnswers() {
        String result = dataAnalyzer.getPercentageOfQuestionsWithAcceptedAnswers();
        System.out.println(result);
    }

    @Test
    public void testDistributionOfQuestionResolutionTime() {
        Map<Long, Integer> result = dataAnalyzer.getDistributionOfQuestionResolutionTime();
        System.out.println(result);
    }

    @Test
    public void testPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes() {
        String result = dataAnalyzer.getPercentageOfQuestionsWithNonAcceptedAnswersHavingMoreUpvotes();
        System.out.println(result);
    }

    @Test
    public void testGetFrequentTagsWithJava() {
        Map<String, Integer> result = dataAnalyzer.getFrequentTagsWithJava();
        System.out.println(result);
    }

    @Test
    public void testGetMostUpvotedTagsOrTagCombinations() {
        Map<String, Integer> result = dataAnalyzer.getMostUpvotedTagsOrTagCombinations();
        System.out.println(result);
    }

    @Test
    public void testGetMostViewedTagsOrTagCombinations() {
        Map<String, Integer> result = dataAnalyzer.getMostViewedTagsOrTagCombinations();
        System.out.println(result);
    }

    @Test
    public void testGetDistributionOfUserParticipation() {
        Map<Integer, Integer> result = dataAnalyzer.getDistributionOfUserParticipation();
        System.out.println(result);
    }

    @Test
    public void testGetMostActiveUsers() {
        List<String> result = dataAnalyzer.getMostActiveUsers();
        System.out.println(result);
    }

    @Test
    public void testGetMostUsedJavaApi() {
        Map<String, Integer> result = javaApiIdentifier.getMostUsedJavaApi();
        System.out.println(result);
    }
}

