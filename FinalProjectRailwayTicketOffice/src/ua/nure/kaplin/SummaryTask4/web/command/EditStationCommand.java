package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStation;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class EditStationCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DaoTrainStation dao = null;
		TrainStation station1 = null;
		TrainStation station2 = null;
		String action = request.getParameter("action");
		String stationName1 = request.getParameter("stationName1");
		String stationName2 = request.getParameter("stationName2");
		
		dao = new DaoTrainStation();
		station2 = new TrainStation();
		if("create".equals(action) && !stationName2.isEmpty()) {
			station2.setStationName(stationName2);
			try {
				dao.insertStation(station2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		station1 = new TrainStation();
		if("update".equals(action) && !stationName2.isEmpty()) {
			station1.setStationName(stationName1);
			station2.setStationName(stationName2);
			try {
				dao.updateTrainStation(station1, station2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return Path.PAGE_ADMIN_MENU_REDIRECT;
	}
	
	
	
	

}
