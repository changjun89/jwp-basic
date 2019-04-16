package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.JdbcTemplate;
import next.model.User;

public class UserDao {

  public void addUser(User user) {
    String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    JdbcTemplate template = new JdbcTemplate();
    template.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
  }

  public void updateUser(User user) throws SQLException {
    String sql = "UPDATE USERS SET PASSWORD=? , NAME =?, EMAIL=? WHERE USERID=?";
    JdbcTemplate template = new JdbcTemplate();
    template.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
  }

  public void removeUser(User user) throws SQLException {
    String sql = "DELETE FROM USERS WHERE USERID=?";
    JdbcTemplate template = new JdbcTemplate();
    template.update(sql, user.getUserId());
  }

  public List<User> findAll() throws SQLException {
    String sql = "SELECT userId, password, name, email FROM USERS";
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    return jdbcTemplate.executeQuery(sql,
        (ResultSet rs) -> {
          return new User(rs.getString("userId")
              , rs.getString("password")
              , rs.getString("name"),
              rs.getString("email"));
        }
    );
  }

  public User findByUserId(String userId) throws SQLException {
    String sql = "SELECT userId, password, name, email FROM USERS where userId=?";
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    return jdbcTemplate.queryForObject(sql, (ResultSet rs) -> {
      return new User(rs.getString("userId")
          , rs.getString("password")
          , rs.getString("name"),
          rs.getString("email"));
    }, userId);
  }

}
