package cse.java2.project.mapper;

import cse.java2.project.domain.model.dto.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.List;

@Mapper
@Component
//Mapper接口中定义了对StackOverflowThread对象的增删改查操作，Mapper XML文件中实现了对应的SQL语句。
public interface StackOverflowThreadMapper {
  void insertQuestion(Question question);

  void insertAnswer(Answer answer);

  @Insert("INSERT INTO comment (comment_id, question_id, post_id, owner_id, edited, creation_date, link, body) " +
      "VALUES (#{comment.commentId}, #{question_id}, #{comment.postId}, #{comment.owner.userId}, #{comment.edited}, #{comment.creationDate}, #{comment.link}, #{comment.body})")
  void insertComment(@Param("comment") Comment comment, @Param("question_id") int question_id);

  void insertOwner(Owner owner);

  @Select("SELECT * FROM answer WHERE question_id = #{questionId}")
  List<Answer> getAnswersByQuestionId(int questionId);

  @Select("SELECT * FROM comment WHERE question_id = #{questionId}")
  List<Comment> getCommentsByQuestionId(int questionId);

  @Select("SELECT * FROM question")
  List<Question> getAllQuestions();

  @Select("SELECT question_id FROM question")
  List<Integer> getAllQuestionIds();

  @Select("SELECT DISTINCT owner_id FROM (SELECT owner_id, question_id FROM question UNION ALL SELECT owner_id, question_id FROM answer UNION ALL SELECT owner_id, post_id as question_id FROM comment) AS temp WHERE temp.question_id = #{questionId}")
  List<String> getParticipantsByQuestionId(int questionId);

  @Select("SELECT DISTINCT owner_id FROM (SELECT owner_id, answer_id FROM answer) AS temp WHERE temp.answer_id = #{answerId}")
  List<String> getParticipantsByAnswerId(int answerId);

  @Select("SELECT DISTINCT owner_id FROM (SELECT owner_id, comment_id FROM comment) AS temp WHERE temp.comment_id = #{commentId}")
  List<String> getParticipantsByCommentId(int commentId);

  @Select("SELECT owner_id, COUNT(*) as participation_count FROM (SELECT owner_id FROM question UNION ALL SELECT owner_id FROM answer UNION ALL SELECT owner_id FROM comment) AS temp GROUP BY owner_id ORDER BY participation_count DESC LIMIT #{limit}")
  List<Map<String, Object>> getMostActiveUsersWithLimit(int limit);

  @Select("SELECT body FROM question")
  List<String> getAllQuestionBodies();

  @Select("SELECT body FROM answer")
  List<String> getAllAnswerBodies();

  @Select("SELECT body FROM comment")
  List<String> getAllCommentBodies();

  @Select("SELECT user_id FROM owner")
  List<Integer> getAllOwnerIds();

  @Select("SELECT answer_id FROM answer")
  List<Integer> getAllAnswerIds();

  @Select("SELECT comment_id FROM comment")
  List<Integer> getAllCommentIds();

  @Select("SELECT * FROM owner")
  List<Owner> getAllOwners();

  @Select("SELECT * FROM answer")
  List<Answer> getAllAnswers();

  @Select("SELECT * FROM comment")
  List<Comment> getAllComments();

  @Select("SELECT display_name FROM owner where user_id = #{ownerId}")
  String getDisplayNameByOwnerId(String ownerId);


}