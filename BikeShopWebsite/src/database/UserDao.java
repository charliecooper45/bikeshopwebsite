package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import security.BCrypt;
import beans.User;

public class UserDao {
	private Connection conn;

	public UserDao(Connection conn) {
		this.conn = conn;
	}

	public boolean validateEmail(User user) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE email=?");
		stmt.setString(1, user.getEmail());

		ResultSet rs = stmt.executeQuery();

		int count = 0;
		if (rs.next()) {
			count = rs.getInt("count");
		}
		rs.close();

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validatePassword(User user) throws SQLException {
		String databasePassword = null;

		PreparedStatement stmt = conn.prepareStatement("SELECT password FROM user WHERE email=?");
		stmt.setString(1, user.getEmail());

		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			databasePassword = rs.getString("password");
			if (BCrypt.checkpw(user.getPassword(), databasePassword)) {
				// the hashed password matches the entered password
				rs.close();
				return true;
			}
		}

		rs.close();
		return false;
	}

	public boolean registerUser(User user) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (email, password, first_name, surname) VALUES (?, ?, ?, ?)");

		try {
			stmt.setString(1, user.getEmail());
			stmt.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getSurname());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int success = stmt.executeUpdate();

		if (success == 0) {
			return false;
		}
		return true;
	}

	public User getUser(String email) throws SQLException {
		User user = new User();
		PreparedStatement stmt = conn.prepareStatement("SELECT first_name, surname FROM user WHERE email = ?");
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String firstName = rs.getString("first_name");
			String surname = rs.getString("surname");
			user.setFirstName(firstName);
			user.setSurname(surname);
			user.setEmail(email);
		}

		return user;
	}
}
