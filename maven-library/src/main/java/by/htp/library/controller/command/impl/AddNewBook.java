package by.htp.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class AddNewBook implements Command {
	private static final Logger log = LogManager.getRootLogger();
	public BookService bookService;

	public AddNewBook() {
	}

	public AddNewBook(BookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public String executeCommand(String request) {
		String[] parameter = request.split(" ");
		String title = parameter[1];
		String author = parameter[2];
		String genre = parameter[3];
		String year = parameter[4];
		String quantity = parameter[5];

		String response = null;

		try {
			bookService.addNewBook(title, genre, author, year, quantity);
			response = "Book successfully added";
		} catch (ServiceException e) {
			response = "Error adding book";
			log.error("Error adding book", e);
			;
		}

		return response;
	}

}
