package cse.java2.project.service.impl;
import cse.java2.project.domain.model.dto.Answer;
import cse.java2.project.domain.model.dto.Comment;
import cse.java2.project.domain.model.dto.Owner;
import cse.java2.project.domain.model.dto.StackOverflowThread;
import cse.java2.project.mapper.StackOverflowThreadMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//添加@Service或@Component注解，以便Spring能够自动检测到它，并将其实例化并添加到容器中
@Service
public class ThreadCollectorApp {

  public static void main(String[] args) {
    ThreadCollector threadCollector = new ThreadCollector();
    String tag = "java";
    int pageSize = 100;
    int pageNum = 5;
    try {
      List<StackOverflowThread> stackOverflowThreads = new ArrayList<>();
        for (int i = 1; i <= pageNum; i++) {
            stackOverflowThreads.addAll(threadCollector.getStackOverflowThreadsByTag(tag, pageSize, i));
        }
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      SqlSession sqlSession = sqlSessionFactory.openSession();
      try {
        StackOverflowThreadMapper mapper = sqlSession.getMapper(StackOverflowThreadMapper.class);
        //插入用户数据
        Set<Owner> users=new LinkedHashSet<>();
        for(StackOverflowThread thread:stackOverflowThreads){
          users.add(thread.getQuestion().getOwner());
          for (Answer answer:thread.getAnswers()){
            users.add(answer.getOwner());
          }
          for (Comment comment:thread.getComments()){
            users.add(comment.getOwner());
          }
        }
        for (Owner owner:users){
          if(owner.getUserId()!=null) {
            mapper.insertOwner(owner);
          }
        }
        //插入问题数据
        for(StackOverflowThread thread : stackOverflowThreads) {
          mapper.insertQuestion(thread.getQuestion()); // 调用Mapper中定义的add方法插入数据
          //插入回答数据
          for (Answer answer:thread.getAnswers()){
            mapper.insertAnswer(answer);
          }
          //插入评论数据
          for (Comment comment:thread.getComments()){
            mapper.insertComment(comment,thread.getQuestion().getQuestionId());
          }
        }
        sqlSession.commit(); // 提交事务
      } finally {
        sqlSession.close(); // 关闭SqlSession
      }

    } catch (IOException e) {
      System.err.println("Error retrieving Stack Overflow threads: " + e.getMessage());
    }
  }
}
