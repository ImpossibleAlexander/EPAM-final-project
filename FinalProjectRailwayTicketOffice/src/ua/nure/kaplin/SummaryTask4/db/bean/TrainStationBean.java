package ua.nure.kaplin.SummaryTask4.db.bean;

import java.util.ArrayList;
import java.util.List;

import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStation;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;

public class TrainStationBean {
	public List<TrainStation> getStations () {
		List<TrainStation> stations = null;
		DaoTrainStation dao = new DaoTrainStation();
		try {
			stations = dao.getStations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stations;
	}
}
