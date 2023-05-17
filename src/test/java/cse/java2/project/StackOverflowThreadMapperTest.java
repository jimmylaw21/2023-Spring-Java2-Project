package cse.java2.project;

import cse.java2.project.domain.model.dto.*;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StackOverflowThreadMapperTest {

    //    @Autowired
//    private StackOverflowThreadMapper stackOverflowThreadMapper;
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    StackOverflowThreadMapper mapper = sqlSession.getMapper(StackOverflowThreadMapper.class);

    public StackOverflowThreadMapperTest() throws IOException {
    }

    @Test
    public void getAllQuestionBodiesTest() {
        // Call the method to get all question bodies
        List<String> questionBodies = mapper.getAllQuestionBodies();

        System.out.println(questionBodies.get(0));

        System.out.println(questionBodies.get(1));

        // Check that the result is not null
        assertThat(questionBodies).isNotNull();

        // Check that the result is not empty
        assertThat(questionBodies).isNotEmpty();

        // Add any additional assertions as needed, e.g., checking specific data points
    }

    @Test
    public void getAllQuestionTest() {
        // Call the method to get all question bodies
        List<Question> questions = mapper.getAllQuestions();

        System.out.println(questions.get(0));

        questions.stream().forEach(a-> System.out.println(a.getQuestionId()));

        // Check that the result is not null
        assertThat(questions).isNotNull();

        // Check that the result is not empty
        assertThat(questions).isNotEmpty();

        // Add any additional assertions as needed, e.g., checking specific data points
    }

    @Test
    public void getAllOwnerTest(){
        List<Owner> owners = mapper.getAllOwners();

        System.out.println(owners.get(0));

        System.out.println(owners.get(1));

        // Check that the result is not null
        assertThat(owners).isNotNull();

        // Check that the result is not empty
        assertThat(owners).isNotEmpty();
    }

    @Test
    public void getAllAnswerTest(){
        List<Answer> answers = mapper.getAllAnswers();

        System.out.println(answers.get(0));

        System.out.println(answers.get(1));

        // Check that the result is not null
        assertThat(answers).isNotNull();

        // Check that the result is not empty
        assertThat(answers).isNotEmpty();
    }

    @Test
    public void getAllIdsTest() {
        // Call the method to get all question bodies
        List<Integer> ownerUserIds = mapper.getAllOwnerIds();

        System.out.println(ownerUserIds.get(0));

        List<Integer> questionIds = mapper.getAllQuestionIds();

        System.out.println(questionIds);

        List<Integer> answerIds = mapper.getAllAnswerIds();

        System.out.println(answerIds.get(0));

        List<Integer> commentIds = mapper.getAllCommentIds();

        System.out.println(commentIds.get(0));

        // Check that the result is not null
        assertThat(ownerUserIds).isNotNull();

        // Check that the result is not empty
        assertThat(ownerUserIds).isNotEmpty();

        // Add any additional assertions as needed, e.g., checking specific data points
    }

}
