package core.jdbc;

import core.exception.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import next.dao.PreparedStatementSetter;
import next.dao.RowMapper;

public class JdbcTemplate {

  public void executeUpate(String sql, PreparedStatementSetter pss) throws DataAccessException {
    try (Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {
      pss.setParam(pstmt);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException();
    }
  }

  public void update(String sql, Object... parameters) throws DataAccessException {
    try (Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {
      for (int i = 0; i < parameters.length; i++) {
        pstmt.setObject(i + 1, parameters[i]);
      }
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException();
    }
  }

  public <T> List<T> executeQuery(String sql, PreparedStatementSetter pss, RowMapper<T> rm)
      throws DataAccessException {
    ResultSet rs = null;
    try (Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {
      pss.setParam(pstmt);
      rs = pstmt.executeQuery();

      List<T> list = new ArrayList<>();
      while (rs.next()) {
        list.add(rm.mapRow(rs));
      }
      return list;
    } catch (SQLException e) {
      throw new DataAccessException();
    }
  }

  public <T> List<T> executeQuery(String sql, RowMapper<T> rm, Object... parameters)
      throws DataAccessException {
    ResultSet rs = null;
    try (Connection con = ConnectionManager.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)) {
      for (int i = 0; i < parameters.length; i++) {
        pstmt.setObject(i + 1, parameters[i]);
      }
      rs = pstmt.executeQuery();

      List<T> list = new ArrayList<>();
      while (rs.next()) {
        list.add(rm.mapRow(rs));
      }
      return list;
    } catch (SQLException e) {
      throw new DataAccessException();
    }
  }


  public <T> T queryForObject(String sql,RowMapper<T> rm,Object... parameters)
      throws SQLException {

    List<T> list = executeQuery(sql,rm,parameters);
    return list.get(0);

  }
}
