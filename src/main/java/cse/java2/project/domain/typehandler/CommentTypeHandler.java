package cse.java2.project.domain.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cse.java2.project.domain.model.dto.Comment;
import cse.java2.project.domain.model.dto.Owner;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class CommentTypeHandler extends BaseTypeHandler<Comment> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Comment parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setInt(1, parameter.getCommentId());

    ps.setInt(2, parameter.getPostId());
    ps.setString(3, parameter.getOwner().getUserId());
    ps.setBoolean(4, parameter.isEdited());
    ps.setLong(5, parameter.getCreationDate());
    ps.setString(6, parameter.getLink());
    ps.setString(7, parameter.getBody());
  }

  @Override
  public Comment getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return createComment(rs, columnName);
  }

  @Override
  public Comment getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return createComment(rs, columnIndex);
  }

  @Override
  public Comment getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return null;
  }


  private Comment createComment(ResultSet rs, Object columnIndex) throws SQLException {
    Comment comment = new Comment();
    comment.setCommentId(rs.getInt("comment_id"));
    comment.setPostId(rs.getInt("post_id"));
    Owner owner = new Owner();
    owner.setUserId(rs.getString("owner_id"));
    comment.setOwner(owner);
    comment.setEdited(rs.getBoolean("edited"));
    comment.setCreationDate(rs.getLong("creation_date"));
    comment.setLink(rs.getString("link"));
    comment.setBody(rs.getString("body"));
    return comment;
  }
}
