package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStationImpl;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class EditStationCommand extends Command {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");
		String page = Path.PAGE_ERROR;
		DaoTrainStationImpl dao = null;
		TrainStation stationOld = null;
		TrainStation stationNew = null;
		String action = request.getParameter("action");
		String stationName1 = request.getParameter("stationName1");
		String stationName2 = request.getParameter("stationName2");

		if (("create".equals(action) && stationName2.isEmpty())
				|| ("delete".equals(action) && stationName1.isEmpty())) {
			throw new AppException("name_of_station_cannot_be_empty");
		}

		dao = new DaoTrainStationImpl();
		stationNew = new TrainStation();
		if ("create".equals(action)) {
			if(stationName2.isEmpty()) {
				throw new AppException("new_name_of_station_cannot_be_empty");
			}
			stationNew.setStationName(stationName2);
			try {
				dao.insertStation(stationNew);
				LOG.trace("Insert station in DB: station --> " + stationNew);
				page = Path.PAGE_ADMIN_MENU_REDIRECT;
			} catch (Exception e) {
				request.setAttribute("errorMessage", "can_not_create_station");
				LOG.trace("Set the request attribute: errorMessage --> " + "Can not create station");
				LOG.error("Can not create station: ", e);
				throw new AppException("can_not_create_station");
			}
		}

		stationOld = new TrainStation();
		if ("update".equals(action)) {
			if(stationName1.isEmpty() || stationName2.isEmpty()) {
				throw new AppException("name_of_stations_cannot_be_empty");
			}
			stationOld.setStationName(stationName1);
			stationNew.setStationName(stationName2);
			try {
				dao.updateTrainStation(stationOld, stationNew);
				LOG.trace("Update station in DB: station old name/new name --> " + stationOld + " / " + stationNew);
				page = Path.PAGE_ADMIN_MENU_REDIRECT;
			} catch (Exception e) {
				LOG.trace("Set the request attribute: errorMessage --> " + "Can not update station");
				LOG.error("Can not update station: ", e);
				throw new AppException("can_not_update_station");
			}
		}

		if ("delete".equals(action)) {
			if(stationName1.isEmpty()) {
				throw new AppException("name_of_station_cannot_be_empty");
			}
			dao = new DaoTrainStationImpl();
			try {
				dao.deleteTrainStation(stationName1);
				page = Path.PAGE_ADMIN_MENU_REDIRECT;
			} catch (Exception e) {
				LOG.trace("Set the request attribute: errorMessage --> " + "Can not delete station");
				LOG.error("Can not delete station", e);
				throw new AppException("can_not_delete_station");
			}
		}

		LOG.debug("Command finished");
		return page;
	}

}
