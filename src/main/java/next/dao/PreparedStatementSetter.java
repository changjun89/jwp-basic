package next.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
  public void setParam(PreparedStatement pstmt) throws SQLException;
}
