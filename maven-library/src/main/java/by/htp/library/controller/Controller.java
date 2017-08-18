package by.htp.library.controller;

import by.htp.library.controller.command.Command;
import by.htp.library.controller.command.CommandProvider;

public final class Controller {
	private final char paramDelimeter = ' ';
	private CommandProvider provider;

	public Controller() {
	}

	public Controller(CommandProvider provider) {
		this.provider = provider;
	}

	public String executeAction(String request) {
		String commandName;
		Command command;

		commandName = request.substring(0, request.indexOf(paramDelimeter));
		command = provider.getCommand(commandName);
		String response = command.executeCommand(request);
		return response;
	}
}
