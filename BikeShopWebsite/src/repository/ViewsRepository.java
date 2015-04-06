package repository;

import hibernate.classes.views.BikeView;

import java.util.List;

/**
 * Manages access to the views in the database. <br>
 * These are used to display table data in the client application.
 * @author Charlie
 * Apr 6, 2015 4:02:29 PM
 */
public interface ViewsRepository {
	List<BikeView> readBikeViews();
}
