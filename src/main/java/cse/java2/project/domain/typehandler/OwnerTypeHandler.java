package cse.java2.project.domain.typehandler;

import cse.java2.project.domain.model.dto.Owner;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerTypeHandler implements TypeHandler<Owner> {

  @Override
  public void setParameter(PreparedStatement ps, int i, Owner parameter, JdbcType jdbcType) throws SQLException {
    ps.setString(1, parameter.getUserId());
    ps.setString(2, parameter.getDisplayName());
    ps.setInt(3, parameter.getReputation());
    ps.setString(4, parameter.getUserType());
    ps.setInt(5, parameter.getAcceptRate());
    ps.setString(6, parameter.getProfileImage());
    ps.setString(7, parameter.getLink());
  }

  @Override
  public Owner getResult(ResultSet rs, String columnName) throws SQLException {
    return buildOwner(rs);
  }

  @Override
  public Owner getResult(ResultSet rs, int columnIndex) throws SQLException {
    return buildOwner(rs);
  }

  @Override
  public Owner getResult(CallableStatement cs, int columnIndex) throws SQLException {
    throw new UnsupportedOperationException("CallableStatement not supported in OwnerTypeHandler.");
  }

  private Owner buildOwner(ResultSet rs) throws SQLException {
    Owner owner = new Owner();
    owner.setUserId(rs.getString("user_id"));
    owner.setDisplayName(rs.getString("display_name"));
    owner.setReputation(rs.getInt("reputation"));
    owner.setUserType(rs.getString("user_type"));
    owner.setAcceptRate(rs.getInt("accept_rate"));
    owner.setProfileImage(rs.getString("profile_image"));
    owner.setLink(rs.getString("link"));
    return owner;
  }
}
