package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private Connection conn;
	
	public UserDao(Connection conn) { 
		this.conn = conn;
	}
	
	public boolean validateEmail(String email) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE email=?");
		stmt.setString(1, email);
		
		ResultSet rs = stmt.executeQuery();
		
		int count = 0;
		if(rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validatePassword(String password) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE password=?");
		stmt.setString(1, password);
		
		ResultSet rs = stmt.executeQuery();
		
		int count = 0;
		if(rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}
	}
}
