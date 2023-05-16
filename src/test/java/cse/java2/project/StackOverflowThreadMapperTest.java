package cse.java2.project;

import cse.java2.project.domain.model.dto.Question;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StackOverflowThreadMapperTest {

    @Autowired
    private StackOverflowThreadMapper stackOverflowThreadMapper;

    @Test
    public void getAllQuestionBodiesTest() {
        // Call the method to get all question bodies
        List<String> questionBodies = stackOverflowThreadMapper.getAllQuestionBodies();

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
        List<Question> questions = stackOverflowThreadMapper.getAllQuestions();

        System.out.println(questions.get(0));

        System.out.println(questions.get(1));

        // Check that the result is not null
        assertThat(questions).isNotNull();

        // Check that the result is not empty
        assertThat(questions).isNotEmpty();

        // Add any additional assertions as needed, e.g., checking specific data points
    }

    @Test
    public void getAllIdsTest() {
        // Call the method to get all question bodies
        List<Integer> ownerUserIds = stackOverflowThreadMapper.getAllOwnerIds();

        System.out.println(ownerUserIds.get(0));

        List<Integer> questionIds = stackOverflowThreadMapper.getAllQuestionIds();

        System.out.println(questionIds);

        List<Integer> answerIds = stackOverflowThreadMapper.getAllAnswerIds();

        System.out.println(answerIds.get(0));

        List<Integer> commentIds = stackOverflowThreadMapper.getAllCommentIds();

        System.out.println(commentIds.get(0));

        // Check that the result is not null
        assertThat(ownerUserIds).isNotNull();

        // Check that the result is not empty
        assertThat(ownerUserIds).isNotEmpty();

        // Add any additional assertions as needed, e.g., checking specific data points
    }
}

