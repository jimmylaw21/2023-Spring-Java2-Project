package cse.java2.project.mapper;

import cse.java2.project.domain.model.dto.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
//Mapper接口中定义了对StackOverflowThread对象的增删改查操作，Mapper XML文件中实现了对应的SQL语句。
public interface StackOverflowThreadMapper {
    void insertQuestion(Question question);

    void insertAnswer(Answer answer);

    @Insert("INSERT INTO comment (comment_id, question_id, post_id, owner_id, edited, creation_date, link, body) " +
            "VALUES (#{comment.commentId}, #{question_id}, #{comment.postId}, #{comment.owner.userId}, #{comment.edited}, #{comment.creationDate}, #{comment.link}, #{comment.body})")
    void insertComment(@Param("comment") Comment comment,@Param("question_id") int question_id);

    void insertOwner(Owner owner);
}
