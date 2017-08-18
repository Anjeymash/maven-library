package by.htp.library.controller.command.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.Book;
import by.htp.library.controller.command.Command;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;


public class GetBookList implements Command {
	private static final Logger log = LogManager.getRootLogger();
	public BookService bookService;

	public GetBookList() {
	}

	public GetBookList(BookService bookService) {
		this.bookService = bookService;
	}

	@Override

	public String executeCommand(String request) {
		List<Book> booklist = null;
		String response = null;
		try {
			booklist = bookService.getBooklist();
			response = "";
			for(Book book: booklist){
				response = response + book.toString()+ "\n";
			}
		} catch (ServiceException e) {
			response = "Error getting list of books";
			log.error("fail in GetBookList", e);
		}
		return response;

	}

}
