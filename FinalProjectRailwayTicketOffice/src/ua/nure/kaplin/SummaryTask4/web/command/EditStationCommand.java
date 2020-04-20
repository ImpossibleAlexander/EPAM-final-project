package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStation;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class EditStationCommand extends Command{
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		DaoTrainStation dao = null;
		TrainStation stationOld = null;
		TrainStation stationNew = null;
		String action = request.getParameter("action");
		String stationName1 = request.getParameter("stationName1");
		String stationName2 = request.getParameter("stationName2");
		
		dao = new DaoTrainStation();
		stationNew = new TrainStation();
		if("create".equals(action) && !stationName2.isEmpty()) {
			stationNew.setStationName(stationName2);
			try {
				dao.insertStation(stationNew);
				LOG.trace("Insert station in DB: station --> " + stationNew);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		stationOld = new TrainStation();
		if("update".equals(action) && !stationName2.isEmpty()) {
			stationOld.setStationName(stationName1);
			stationNew.setStationName(stationName2);
			try {
				dao.updateTrainStation(stationOld, stationNew);
				LOG.trace("Update station in DB: station old name/new name --> " + stationOld + " / " + stationNew);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		LOG.debug("Command finished");
		return Path.PAGE_ADMIN_MENU_REDIRECT;
	}
	
	
	
	

}
