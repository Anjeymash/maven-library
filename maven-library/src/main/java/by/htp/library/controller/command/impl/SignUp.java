package by.htp.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.controller.command.Command;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class SignUp implements Command {
	private static final Logger log = LogManager.getRootLogger();
	public UserService userService;

	public SignUp() {
	}

	public SignUp(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String executeCommand(String request) {
		String[] parameter = request.split(" ");
		String login = parameter[1];
		String password = parameter[2];
		String response = null;

		try {
			userService.signUp(login, password);
			response = "User was registrated " + login;
		} catch (ServiceException e) {
			response = "Sign up error";
			log.error("Sign up error", e);
		}

		return response;
	}

}
