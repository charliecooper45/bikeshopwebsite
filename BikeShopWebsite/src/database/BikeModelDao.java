package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BikeModel;
import beans.Brand;

public class BikeModelDao {
	private Connection conn;

	public BikeModelDao(Connection conn) {
		this.conn = conn;
	}

	public List<BikeModel> getBikeModels(Brand brand) throws SQLException {
		List<BikeModel> list = new ArrayList<>();
		
		PreparedStatement stmt = conn.prepareStatement("SELECT name, price, image FROM bike_model WHERE brand_id = ?");
		stmt.setInt(1, brand.getId());

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String name = rs.getString("name");
			String price = rs.getString("price");
			String image = rs.getString("image");
			BikeModel model = new BikeModel(name, price, brand, image);
			list.add(model);
		}
		return list;
	}

}
