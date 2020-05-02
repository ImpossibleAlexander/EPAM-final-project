package ua.nure.kaplin.SummaryTask4.exception;

public class Messages {

	private Messages() {
		// no op
	}
	

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";
	
	public static final String ERR_CANNOT_INSERT_USER = "Cannot insert a user";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
	
	public static final String ERR_CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction";

	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
	
	public static final String ERR_CANNOT_UPDATE_ROUTE_POINTS = "Can not update route points";
	
	public static final String ERR_CANNOT_SELECT_ROUTE_POINT_BY_TRAIN_NUMBER = "Can not select route point by train number";
	
	public static final String ERR_CANNOT_INSERT_ROUTE = "Can not insert route";

	public static final String ERR_CANNOT_INSERT_ROUTE_POINT = "Can not create route point";
	
	public static final String ERR_CANNOT_INSERT_TICKET = "Can not insert ticket";

	public static final String ERR_CANNOT_INSERT_USER_TICKET = "Can not insert user ticket";
	
	public static final String ERR_CANNOT_SELECT_TRAIN_NUMBER_BY_STATION_NAME = "Can not select train number by station name";
	
	public static final String ERR_CANNOT_SELECT_TRAIN_BY_TRAIN_NUMBER = "Can not select train by train number";
	
	public static final String ERR_CANNOT_INSERT_STATION = "Can not insert station";
	
	public static final String ERR_CANNOT_SELECT_STATION_BY_NAME = "Can not select station by name";
	
	public static final String ERR_CANNOT_SELECT_STATIONS = "Can not select stations";
	
	public static final String ERR_CANNOT_UPDATE_STATION = "Can not update station";
	
	public static final String ERR_DELETE_STATION = "Can not delete station";
	
	public static final String ERR_UPDATE_TRAIN = "Can not update train";
	
	public static final String ERR_CANNOT_CLOSE_DELEGATING_CALLABLE_STATEMENT = "Can not close delegating callable statement";
	
	public static final String ERR_CANNOT_FIND_USER_BY_USER_ID = "Can not find user by user id";
}