package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.FilmDAO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import models.Film;
import models.FilmList;

@WebServlet("/filmsControllerAPI")
public class filmsControllerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public filmsControllerAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	FilmDAO dao = FilmDAO.INSTANCE;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("cache-control", "no-cache");
		response.setHeader("pragma", "no-cache");

		String format = request.getHeader("Accept");
		PrintWriter out = response.getWriter();
		

		if ("application/json".equals(format)) {
			response.setContentType("application/json");
			ArrayList<Film> allFilms = dao.getAllFilms();
			Gson gson = new Gson();
			String json = gson.toJson(allFilms);
			out.write(json);
			System.out.println(json);
			out.close();
		} else if ("application/xml".equals(format)) {
			try {
				response.setContentType("application/xml");
				ArrayList<Film> allFilms = dao.getAllFilms();

				FilmList fl = new FilmList(allFilms);
				System.out.print(fl);
				StringWriter sw = new StringWriter();
				JAXBContext context = JAXBContext.newInstance(FilmList.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(fl, sw);
				String xml = sw.toString();
				out.write(xml);
				System.out.println("print XML");
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} else if ("text/plain".equals(format)) {
			// Converting a Java object to String format
			response.setContentType("text/plain");

			ArrayList<Film> allFilms = dao.getAllFilms();
			for (int i = 0; i < allFilms.size(); i++) {
				Film currentFilm = allFilms.get(i);
				String string = currentFilm.getId() + "#" + currentFilm.getTitle() + "#" + currentFilm.getYear() + "#"
						+ currentFilm.getDirector() + "#" + currentFilm.getStars() + "#" + currentFilm.getReview();
				if (i < allFilms.size()) {
					string = string + "\r\n";
				}
				out.write(string);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String title = request.getParameter("title");
		int year = Integer.valueOf(request.getParameter("year"));
		String director = request.getParameter("director");
		String starts = request.getParameter("stars");
		String review = request.getParameter("review");

		Film insertFilm = new Film(title, year, director, starts, review);

		try {
			dao.insertFilm(insertFilm);
			out.write("Film Inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();
		response.sendRedirect("./selectAll");
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		int id = Integer.valueOf(request.getParameter("id"));
		String title = request.getParameter("title");
		int year = Integer.valueOf(request.getParameter("year"));
		String director = request.getParameter("director");
		String starts = request.getParameter("stars");
		String review = request.getParameter("review");

		Film updateFilm = new Film(id, title, year, director, starts, review);

		try {
			dao.updateFilm(updateFilm);
			out.write("film updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		int id = Integer.valueOf(request.getParameter("id"));
		Film f = new Film();
		f.setId(id);

		try {
			dao.deleteFilm(id);
			out.write("film deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.close();
	}
}
