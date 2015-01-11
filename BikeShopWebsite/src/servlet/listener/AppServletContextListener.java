package servlet.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import database.monitoring.DatabaseConnectionMonitorThread;

@WebListener
public class AppServletContextListener implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(AppServletContextListener.class);
	private Timer timer;
	
	public AppServletContextListener() {
		timer = new Timer(true);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//TODO: start a thread here which monitors the database connection and sets a static value that can be checked, should be a daemon thread
		LOG.info("Starting DatabaseConnectionMonitorThread...");
		DatabaseConnectionMonitorThread thread = new DatabaseConnectionMonitorThread();
		timer.scheduleAtFixedRate(thread, 0, 10 * 1000);

	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		LOG.info("Stopping DatabaseConnectionMonitorThread...");
		timer.cancel();
	}
}
