package controllers;

import hibernate.classes.BikeModel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;

/**
 * Servlet implementation class BikeController
 */
@WebServlet("/BikeController")
public class BikeController extends AbstractController {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int brandId = Integer.parseInt(request.getParameter("brand"));
		List<BikeModel> models = doLookupBikes(brandId);
		//TODO: implement bike models jsp page
	}
	
	@SuppressWarnings("unchecked")
	private List<BikeModel> doLookupBikes(int brandId) {
		LOG.info("Looking up bikes for brand " + brandId);
		Query namedQuery = session.getNamedQuery(BikeModel.QUERY_BY_BRAND_ID);
		namedQuery.setParameter("brandId", brandId);
		List<BikeModel> bikeModels = namedQuery.list();
		LOG.info("Found " + bikeModels.size() + " bike models for brand " + brandId);
		return bikeModels;
	}

}
