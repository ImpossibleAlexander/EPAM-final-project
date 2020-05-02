package ua.nure.kaplin.SummaryTask4.DAO;

import java.util.List;

import ua.nure.kaplin.SummaryTask4.db.entity.Train;

public interface DaoTrain {
	List<Train> findTrainNumberByStationName(String departureStation, String arriveStation) throws Exception;
	Train findTrainByNumber(int number) throws Exception;
	boolean updateTrain(Train train) throws Exception;
}
