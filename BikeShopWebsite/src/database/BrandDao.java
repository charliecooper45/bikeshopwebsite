package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Brand;

public class BrandDao {
	private Connection conn;

	public BrandDao(Connection conn) {
		this.conn = conn;
	}

	public List<Brand> getBrands() throws SQLException {
		List<Brand> list = new ArrayList<>();
		PreparedStatement stmt = conn.prepareStatement("SELECT name FROM bike_brands");

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String name = rs.getString("name");
			list.add(new Brand(name));
		}
		return list;
	}
}
