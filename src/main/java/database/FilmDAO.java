package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Film;

import java.sql.*;

public enum FilmDAO {

	INSTANCE;
	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;
	String user = "balajoba";
	String password = "infgerpL5";
	// Note none default port used, 6306 not 3306
	String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;

	private FilmDAO() {
	}

	boolean connected = false;

	private void openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

	public ArrayList<Film> getAllFilms() {

		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from films";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				allFilms.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return allFilms;
	}

	public Film insertFilm(Film f) throws SQLException {
		openConnection();
		oneFilm = null;
		// Create insert statement and run it
		try {
			String selectSQL = "insert into films (title, year, director, stars, review) values ('" + f.getTitle()
					+ "','" + f.getYear() + "','" + f.getDirector() + "','" + f.getStars() + "','" + f.getReview()
					+ "');";
			stmt.executeUpdate(selectSQL);
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneFilm;
	}

	public Film getFilmByID(int id) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where id=" + id;
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
			}
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneFilm;
	}
	
	public Film getFilmByName(String title) {
		
		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where Title=" + title;
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
			}
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		
		return oneFilm;
	}

	public Film deleteFilm(int id) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "DELETE FROM films WHERE id = " + id;
			stmt.executeUpdate(selectSQL);
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneFilm;
	}
	
	public Film updateFilm(Film f) throws SQLException {
		openConnection();
		oneFilm = null;
		// Update contact where id = ...
		try {
			String updateSQL = "UPDATE films SET Title='" + f.getTitle()+"', Year='"+f.getYear()+"', Director='"+f.getDirector()+"', Stars='"+ f.getStars()+"', Review='"+f.getReview()+"' WHERE id ="+f.getId()+";";
			System.out.println(updateSQL);
			stmt.executeUpdate(updateSQL);
			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}
		
		return oneFilm;
	}
}
