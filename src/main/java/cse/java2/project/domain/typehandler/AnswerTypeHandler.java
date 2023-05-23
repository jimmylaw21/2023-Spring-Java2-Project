package cse.java2.project.domain.typehandler;

import java.sql.*;

import org.apache.ibatis.type.*;

import cse.java2.project.domain.model.dto.*;

@MappedTypes(Answer.class)
public class AnswerTypeHandler extends BaseTypeHandler<Answer> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Answer answer, JdbcType jdbcType) throws SQLException {
    ps.setInt(1, answer.getAnswerId());
    ps.setInt(2, answer.getQuestionId());
    ps.setString(3, answer.getOwner().getUserId());
    ps.setInt(4, answer.getDownVoteCount());
    ps.setInt(5, answer.getUpVoteCount());
    ps.setBoolean(6, answer.isAccepted());
    ps.setInt(7, answer.getScore());
    ps.setLong(8, answer.getLastActivityDate());
    ps.setLong(9, answer.getLastEditDate());
    ps.setLong(10, answer.getCreationDate());
    ps.setString(11, answer.getLink());
    ps.setString(12, answer.getTitle());
    ps.setString(13, answer.getBody());
  }

  @Override
  public Answer getNullableResult(ResultSet rs, String columnName) throws SQLException {
    Answer answer = new Answer();
    answer.setAnswerId(rs.getInt("answer_id"));
    answer.setQuestionId(rs.getInt("question_id"));
    Owner owner = new Owner();
    owner.setUserId(rs.getString("owner_id"));
    answer.setOwner(owner);
    answer.setDownVoteCount(rs.getInt("down_vote_count"));
    answer.setUpVoteCount(rs.getInt("up_vote_count"));
    answer.setAccepted(rs.getBoolean("is_accepted"));
    answer.setScore(rs.getInt("score"));
    answer.setLastActivityDate(rs.getLong("last_activity_date"));
    answer.setLastEditDate(rs.getLong("last_edit_date"));
    answer.setCreationDate(rs.getLong("creation_date"));
    answer.setLink(rs.getString("link"));
    answer.setTitle(rs.getString("title"));
    answer.setBody(rs.getString("body"));
    return answer;
  }

  @Override
  public Answer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    Answer answer = new Answer();
    answer.setAnswerId(rs.getInt("answer_id"));
    answer.setQuestionId(rs.getInt("question_id"));
    Owner owner = new Owner();
    owner.setUserId(rs.getString("owner_id"));
    answer.setOwner(owner);
    answer.setDownVoteCount(rs.getInt("down_vote_count"));
    answer.setUpVoteCount(rs.getInt("up_vote_count"));
    answer.setAccepted(rs.getBoolean("is_accepted"));
    answer.setScore(rs.getInt("score"));
    answer.setLastActivityDate(rs.getLong("last_activity_date"));
    answer.setLastEditDate(rs.getLong("last_edit_date"));
    answer.setCreationDate(rs.getLong("creation_date"));
    answer.setLink(rs.getString("link"));
    answer.setTitle(rs.getString("title"));
    answer.setBody(rs.getString("body"));
    return answer;
  }

  @Override
  public Answer getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    // Not implemented
    return null;
  }
}

