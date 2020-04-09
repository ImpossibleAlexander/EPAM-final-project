package ua.nure.kaplin.SummaryTask4.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStation;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;

@WebServlet("adding")
public class Controller extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isAdded;
		String message;
	    String stationName = request.getParameter("name");
	    System.out.println(stationName + "-----------");
	    DaoTrainStation dao = new DaoTrainStation();
	    TrainStation station = new TrainStation();
	    station.setStationName(stationName);
	    try {
			dao.insertStation(station);
			message = "SUCCESS";
		} catch (Exception e) {
			message = "FAILED";
			e.printStackTrace();
		}   
	    request.setAttribute("isAdded", message);
	    request.getRequestDispatcher("train.jsp").forward(request, response);
	}
}
