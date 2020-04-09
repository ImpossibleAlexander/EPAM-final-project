package ua.nure.kaplin.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * Holder for all commands.<br/>
 * 
 * @author D.Kolesnikov
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		// common commands
		commands.put("route", new GetRouteCommand());
		commands.put("routeDetails", new GetRouteDetailsCommand());
		commands.put("mapping", new PageMappingCommand());
		commands.put("registerUser", new RegistrationCommand());
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("adminEditStation", new EditStationCommand());
		commands.put("editRoute", new EditRouteCommand());
		commands.put("routePointUpdateCommand", new RoutePointUpdateCommand());
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}