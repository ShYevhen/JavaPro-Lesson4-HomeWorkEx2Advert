package net.ukr.shyevhen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adverts")
public class AdvertsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarsList list = new CarsList();

	@Override
	public void init() throws ServletException {
		list.addAdvert(new Car("admin", "The best car", "its all", 120000, 321654987896L, "Ford", "Mustang", 1996));
		list.addAdvert(new Car("alladin", "new car", "My best car", 110000, 789654987896L, "Nisan", "Skyline", 2000));
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String priceMin = req.getParameter("priceMin");
		String priceMax = req.getParameter("priceMax");
		String brand = req.getParameter("brand");
		String model = req.getParameter("model");
		String yearSt = req.getParameter("year");
		String advertSt = req.getParameter("getAdvert");
		if (advertSt != null && !"".equals(advertSt)) {
			int hash = Integer.parseInt(advertSt);
			Car car = list.get(hash);
			req.setAttribute("getAdvert", car);
		} else if (priceMin != null && priceMin != null && !"".equals(priceMin) && !"".equals(priceMax)) {
			int min = priceMin == null ? 0 : Integer.parseInt(priceMin);
			int max = priceMax == null ? 0 : Integer.parseInt(priceMax);
			req.setAttribute("price", "from " + min + " to " + max);
			req.setAttribute("list", list.get(min, max));
		} else {
			modelBrendYear(req, brand, model, yearSt);
		}
		RequestDispatcher dispar = getServletContext().getRequestDispatcher("/results.jsp");
		dispar.forward(req, resp);
	}

	private void modelBrendYear(HttpServletRequest req, String brand, String model, String yearSt) {
		if (brand != null && !"".equals(brand)) {
			req.setAttribute("brand", brand);
			req.setAttribute("list", list.get("brand", brand));
		} else if (model != null && !"".equals(model)) {
			req.setAttribute("model", model);
			req.setAttribute("list", list.get("model", model));
		} else if (yearSt != null && !"".equals(yearSt)) {
			int year = Integer.parseInt(yearSt);
			req.setAttribute("year", year);
			req.setAttribute("list", list.get("year", year));
		} else {
			req.setAttribute("all", "all");
			req.setAttribute("list", list.getCarList());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String doing = req.getParameter("doing");
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user_login");
		if (doing.equals("add") && user != null && !"".equals(user)) {
			String title = req.getParameter("title");
			String specification = req.getParameter("specification");
			String priceSt = req.getParameter("price");
			int price = Integer.parseInt(priceSt);
			String telephone = req.getParameter("telephone");
			long tel = Long.parseLong(telephone);
			String brand = req.getParameter("brand");
			String model = req.getParameter("model");
			String yearSt = req.getParameter("year");
			int year = Integer.parseInt(yearSt);
			Car car = new Car(user, title, specification, price, tel, brand, model, year);
			car.setNewCar(req.getParameter("newcar") != null);
			list.addAdvert(car);
			resp.sendRedirect("adverts?getAdvert=" + car.hashCode());
			return;
		} else if (doing.equals("del") && user != null && !"".equals(user)) {
			String hashSt = req.getParameter("hash");
			int hash = Integer.parseInt(hashSt);
			list.delAdvert(user, hash);
		}
		resp.sendRedirect("adverts");
	}

}
