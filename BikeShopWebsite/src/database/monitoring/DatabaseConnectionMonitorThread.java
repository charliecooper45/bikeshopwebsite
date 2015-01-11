package database.monitoring;

import hibernate.utils.HibernateUtilities;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;

public class DatabaseConnectionMonitorThread extends TimerTask {
	private static final Logger LOG = Logger.getLogger(DatabaseConnectionMonitorThread.class);
	private Configuration configuration;
	private String url;
	private String user;
	private String password;
	
	public DatabaseConnectionMonitorThread() {
		configuration = new Configuration().configure();
		url = configuration.getProperty("hibernate.connection.url");
		user = configuration.getProperty("hibernate.connection.username");
		password = configuration.getProperty("hibernate.connection.password");
	}
	
	@Override
	public void run() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			DriverManager.getConnection(url, user, password).close();
			LOG.info("Database connection to database at " + url + " successful");
			HibernateUtilities.setConnectedToDatabase(true);
		} catch (SQLException | ClassNotFoundException e) {
			LOG.info("Database connection to database at " + url + " failed: " + e.getMessage());
			HibernateUtilities.setConnectedToDatabase(false);
		}
	}
}
