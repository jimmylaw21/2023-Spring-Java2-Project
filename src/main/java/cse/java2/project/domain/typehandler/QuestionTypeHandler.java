package cse.java2.project.domain.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cse.java2.project.domain.model.dto.Owner;
import cse.java2.project.domain.model.dto.Question;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.util.Arrays;
import java.util.Objects;

public class QuestionTypeHandler extends BaseTypeHandler<Question> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Question parameter, JdbcType jdbcType) throws SQLException {
    ps.setInt(1, parameter.getQuestionId());
    ps.setString(2, parameter.getTitle());
    ps.setString(3, parameter.getBody());
    ps.setArray(4, ps.getConnection().createArrayOf("text", parameter.getTags().toArray()));
    ps.setString(5, parameter.getOwner().getUserId());
    ps.setInt(6, parameter.getOwner().getReputation());
    ps.setBoolean(7, parameter.isAnswered());
    ps.setInt(8, parameter.getViewCount());
    ps.setInt(9, parameter.getFavoriteCount());
    ps.setInt(10, parameter.getDownVoteCount());
    ps.setInt(11, parameter.getUpVoteCount());
    ps.setInt(12, parameter.getAnswerCount());
    ps.setInt(13, parameter.getScore());
    ps.setLong(14, parameter.getLastActivityDate());
    ps.setLong(15, parameter.getCreationDate());
    ps.setLong(16, parameter.getLastEditDate());
    ps.setString(17, parameter.getLink());
  }

  @Override
  public Question getNullableResult(ResultSet rs, String columnName) throws SQLException {
    Question question = new Question();
    question.setQuestionId(rs.getInt("question_id"));
    question.setTitle(rs.getString("title"));
    question.setBody(rs.getString("body"));
    question.setTags(Arrays.asList((String[]) rs.getArray("tags").getArray()));
    Owner owner = new Owner();
    owner.setUserId(rs.getString("owner_id"));
    owner.setReputation(rs.getInt("owner_reputation"));
    question.setOwner(owner);
    question.setAnswered(rs.getBoolean("is_answered"));
    question.setViewCount(rs.getInt("view_count"));
    question.setFavoriteCount(rs.getInt("favorite_count"));
    question.setDownVoteCount(rs.getInt("down_vote_count"));
    question.setUpVoteCount(rs.getInt("up_vote_count"));
    question.setAnswerCount(rs.getInt("answer_count"));
    question.setScore(rs.getInt("score"));
    question.setLastActivityDate(rs.getLong("last_activity_date"));
    question.setCreationDate(rs.getLong("creation_date"));
    question.setLastEditDate(rs.getLong("last_edit_date"));
    question.setLink(rs.getString("link"));
    return question;
  }

  @Override
  public Question getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    Question question = new Question();
    question.setQuestionId(rs.getInt("question_id"));
    question.setTitle(rs.getString("title"));
    question.setBody(rs.getString("body"));
    question.setTags(Arrays.asList((String[]) rs.getArray("tags").getArray()));
    Owner owner = new Owner();
    owner.setUserId(rs.getString("owner_id"));
    owner.setReputation(rs.getInt("owner_reputation"));
    question.setOwner(owner);
    question.setAnswered(rs.getBoolean("is_answered"));
    question.setViewCount(rs.getInt("view_count"));
    question.setFavoriteCount(rs.getInt("favorite_count"));
    question.setDownVoteCount(rs.getInt("down_vote_count"));
    question.setUpVoteCount(rs.getInt("up_vote_count"));
    question.setAnswerCount(rs.getInt("answer_count"));
    question.setScore(rs.getInt("score"));
    question.setLastActivityDate(rs.getLong("last_activity_date"));
    question.setCreationDate(rs.getLong("creation_date"));
    question.setLastEditDate(rs.getLong("last_edit_date"));
    question.setLink(rs.getString("link"));
    return question;
  }

  @Override
  public Question getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    Question question = new Question();
    question.setQuestionId(cs.getInt(columnIndex));
    question.setTitle(cs.getString("title"));
    question.setBody(cs.getString("body"));
    question.setTags(Arrays.asList((String[]) cs.getArray("tags").getArray()));
    Owner owner = new Owner();
    owner.setUserId(cs.getString("owner_id"));
    owner.setReputation(cs.getInt("owner_reputation"));
    question.setOwner(owner);
    question.setAnswered(cs.getBoolean("is_answered"));
    question.setViewCount(cs.getInt("view_count"));
    question.setFavoriteCount(cs.getInt("favorite_count"));
    question.setDownVoteCount(cs.getInt("down_vote_count"));
    question.setUpVoteCount(cs.getInt("up_vote_count"));
    question.setAnswerCount(cs.getInt("answer_count"));
    question.setScore(cs.getInt("score"));
    question.setLastActivityDate(cs.getLong("last_activity_date"));
    question.setCreationDate(cs.getLong("creation_date"));
    question.setLastEditDate(cs.getLong("last_edit_date"));
    question.setLink(cs.getString("link"));
    return question;
  }
}
