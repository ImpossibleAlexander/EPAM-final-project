package ua.nure.kaplin.SummaryTask4;

import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStation;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;

public class Test {
	public static void main(String[] args) throws Exception {
		DaoTrainStation dao = new DaoTrainStation();
		TrainStation station = new TrainStation();
		station.setStationName("goods");
		dao.insertStation(station);
		
	}
}
