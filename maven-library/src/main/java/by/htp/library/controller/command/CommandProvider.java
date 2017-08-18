package by.htp.library.controller.command;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CommandProvider {
	private Map<CommandName, Command> repository;
	private static final Logger log = LogManager.getRootLogger();

	public CommandProvider() {
	}

	public CommandProvider(Map<CommandName, Command> repository) {
		super();
		this.repository = repository;
	}

	public Command getCommand(String key) {
		Command command;
		CommandName commandName;

		try {
			commandName = CommandName.valueOf(key.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			log.error("Wrong request", e);
			command = repository.get(CommandName.WRONG_REQUEST);
		}

		return command;
	}

}
