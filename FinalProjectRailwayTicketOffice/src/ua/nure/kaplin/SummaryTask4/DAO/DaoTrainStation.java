package ua.nure.kaplin.SummaryTask4.DAO;

import java.util.List;

import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;

public interface DaoTrainStation {
	
	public TrainStation findStationByName(String login) throws Exception;

	public TrainStation findStationById(int id) throws Exception;

	public List<TrainStation> getStations() throws Exception;

}
