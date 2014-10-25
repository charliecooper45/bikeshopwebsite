package database;

import java.sql.Connection;


public class DaoFactory {
	public static UserDao getUserDao(Connection conn) {
		return new UserDao(conn);
	}
	
	public static BrandDao getBrandDao(Connection conn) {
		return new BrandDao(conn);
	}
	
	public static BikeModelDao getBikeModelDao(Connection conn) {
		return new BikeModelDao(conn);
	}
}
