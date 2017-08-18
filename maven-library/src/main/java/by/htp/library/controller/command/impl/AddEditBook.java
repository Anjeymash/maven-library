package by.htp.library.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;


public class AddEditBook implements Command {
	private static final Logger log = LogManager.getRootLogger();
	public BookService bookService;
	public AddEditBook() {}
	public AddEditBook(BookService bookService) {
		this.bookService = bookService;
	}
	@Override
	public String executeCommand(String request) {
		String [] parameter = request.split(" ");
		String title = parameter[1];
		String author = parameter[2];
		String genre = parameter[3];
		String year = parameter[4];
		String quantity = parameter[5];
		String idBook = parameter[6];

		
		String response = null;
		
		try {
			bookService.addEditBook(title, genre, author, year, quantity, idBook);
			response = "Book successfully edited";
		} catch (ServiceException e) {
			response = "Error editing book";
			log.error("Error editing book", e);
		}
		
		return response;
	}

}
