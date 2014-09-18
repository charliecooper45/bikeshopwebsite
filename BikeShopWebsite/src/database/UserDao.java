package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDao {
	private Connection conn;
	
	public UserDao(Connection conn) { 
		this.conn = conn;
	}
	
	public boolean checkLogin(String email, String password) {
		// PreparedStatement stmt = conn.prepareStatement("");
		return true;
	}
}
