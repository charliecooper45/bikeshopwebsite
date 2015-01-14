package controllers;

import hibernate.classes.BikeModel;
import hibernate.classes.Brand;

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
		doLookupBikes(brandId, request);
		//TODO: implement bike models jsp page
		getServletContext().getRequestDispatcher("/bikes.jsp").forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void doLookupBikes(int brandId, HttpServletRequest request) {
		//TODO: maybe attempt to pass this data to the controller?
		//TODO: look for tool to check for unused code
		LOG.info("Looking up information for brand " + brandId);
		Query namedQuery = session.getNamedQuery(Brand.QUERY_BY_ID);
		namedQuery.setParameter("id", brandId);
		Brand brand = (Brand) namedQuery.uniqueResult();
		request.setAttribute("brand", brand);
		
		LOG.info("Looking up bikes for brand " + brandId);
		namedQuery = session.getNamedQuery(BikeModel.QUERY_BY_BRAND_ID);
		namedQuery.setParameter("brandId", brandId);
		List<BikeModel> bikeModels = namedQuery.list();
		LOG.info("Found " + bikeModels.size() + " bike models for brand " + brandId);
		request.setAttribute("bikeModels", bikeModels);
	}

}
