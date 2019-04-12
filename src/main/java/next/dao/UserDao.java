package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import core.jdbc.SelectJdbcTemplate;
import next.model.User;

public class UserDao {
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setParam(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,user.getUserId());
                pstmt.setString(2,user.getPassword());
                pstmt.setString(3,user.getName());
                pstmt.setString(4,user.getEmail());
            }
        };
        template.executeUpate(sql);
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE USERS SET PASSWORD=? , NAME =?, EMAIL=? WHERE USERID=?";
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setParam(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        };
        template.executeUpate(sql);
    }

    public void removeUser(User user) throws SQLException {
        String sql = "DELETE FROM USERS WHERE USERID=?";
        JdbcTemplate template = new JdbcTemplate() {
            @Override
            public void setParam(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
            }
        };
        template.executeUpate(sql);
    }

    public List<User> findAll() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
                userList.add(user);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return userList;
    }

    public User findByUserId(String userId) throws SQLException {
        String sql = "SELECT userId, password, name, email FROM USERS where userId=?";
        SelectJdbcTemplate jdbcTemplate = new SelectJdbcTemplate() {
            @Override
            public void setParam(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,userId);
            }

            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                if(!rs.next()) {
                    return null;
                };
                return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }
        };
        return jdbcTemplate.executeQuery(sql);
    }


}
