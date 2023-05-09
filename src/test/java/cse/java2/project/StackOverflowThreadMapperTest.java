package cse.java2.project;

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

        // Check that the result is not null
        assertThat(questionBodies).isNotNull();

        // Check that the result is not empty
        assertThat(questionBodies).isNotEmpty();

        // Add any additional assertions as needed, e.g., checking specific data points
    }
}

