package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainImpl;
import ua.nure.kaplin.SummaryTask4.db.entity.Train;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class UpdateTrainInfoCommand extends Command{

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		String page = Path.PAGE_ERROR;
		Train train = null;
		DaoTrainImpl dao = null;
		String trainID = request.getParameter("trainID");
		String trainNumber = request.getParameter("trainNumber");
		String coupe = request.getParameter("coupe");
		String reservedSeat = request.getParameter("reservedSeat");
		String common = request.getParameter("common");
		String coupePrice = request.getParameter("coupePrice");
		String reservedSeatPrice = request.getParameter("reservedSeatPrice");
		String commonPrice = request.getParameter("commonPrice");
		String trainStatus = request.getParameter("trainStatus");
		
		if(trainID == null || trainID.isEmpty()
				||trainNumber == null || trainNumber.isEmpty()
				||coupe == null || coupe.isEmpty()
				||reservedSeat == null || reservedSeat.isEmpty()
				||common == null || common.isEmpty()
				||coupePrice == null || coupePrice.isEmpty()
				||reservedSeatPrice == null || reservedSeatPrice.isEmpty()
				||commonPrice == null || commonPrice.isEmpty()
				||trainStatus == null || trainStatus.isEmpty()) {
				throw new AppException("empty_fields");
			}
		
		train = new Train();
		train.setId(Integer.parseInt(trainID));
		train.setTrainNumber(Integer.parseInt(trainNumber));
		train.setCoupe(Integer.parseInt(coupe));
		train.setReservedSeat(Integer.parseInt(reservedSeat));
		train.setCommon(Integer.parseInt(common));
		train.setCoupePrice(Integer.parseInt(coupePrice));
		train.setReservedSeatPrice(Integer.parseInt(reservedSeatPrice));
		train.setCommonPrice(Integer.parseInt(commonPrice));
		train.setTrainStatus(trainStatus);
		
		dao = new DaoTrainImpl();		
		try {
			dao.updateTrain(train);
			page = Path.PAGE_MAIN_FOR_ADMIN_REDIRECT;
		} catch (Exception e) {
			request.setAttribute("errorMessage", "cannot_update_train_info");
			LOG.error("Update train: ", e);
		}
		return page;
	}
	
}
