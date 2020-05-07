package ua.nure.kaplin.SummaryTask4.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.kaplin.SummaryTask4.db.entity.RouteTest;
import ua.nure.kaplin.SummaryTask4.db.entity.TicketTest;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStationTest;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainTest;
import ua.nure.kaplin.SummaryTask4.db.entity.UserTest;
import ua.nure.kaplin.SummaryTask4.validator.FieldsValidatorTest;
import ua.nure.kaplin.SummaryTask4.web.command.PageMappingCommandTest;

@RunWith(Suite.class)
@SuiteClasses({
	RouteTest.class,
	TicketTest.class,
	PageMappingCommandTest.class,
	TrainTest.class,
	TrainStationTest.class,
	UserTest.class,
	FieldsValidatorTest.class
	})
public class RunTests {}
