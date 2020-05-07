package ua.nure.kaplin.SummaryTask4.DAO;

import java.util.List;

import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;

public interface DaoTrainStation {
	
	 void insertStation(TrainStation station) throws Exception;
	
	public TrainStation findStationByName(String login) throws Exception;

	boolean updateTrainStation(TrainStation trainStationBeforeUpdate, TrainStation trainStationAfterUpdate) throws Exception;
	
	public List<TrainStation> getStations() throws Exception;
	
	boolean deleteTrainStation(String name) throws Exception;

}
