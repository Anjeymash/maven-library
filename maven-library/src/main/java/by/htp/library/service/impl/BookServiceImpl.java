package by.htp.library.service.impl;

import java.util.IllegalFormatException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.validation.ValidationData;

public class BookServiceImpl implements BookService {

	private static final Logger log = LogManager.getRootLogger();
	public BookDAO bookDAO;

	public BookServiceImpl() {
	}

	public BookServiceImpl(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public void addNewBook(String title, String genre, String author, String year, String quantityStr)
			throws ServiceException {
		if (!ValidationData.validBook(title, genre, author, year, quantityStr)) {
			throw new ServiceException("Incorrect data about book");
		}

		int quantity = 0;
		try {
			quantity = Integer.parseInt(quantityStr);
		} catch (IllegalFormatException e) {
			log.error("Year format error", e);
			throw new ServiceException("Year format exception");
		}

		try {
			bookDAO.addNewBook(title, author, genre, year, quantity);
		} catch (DAOException e) {
			log.error("Error adding a book to the library", e);
			throw new ServiceException("Error adding a book to the library");
		}

	}

	@Override
	public void addEditBook(String title, String genre, String author, String year, String quantityStr,
			String idBookStr) throws ServiceException {
		if (!ValidationData.validBook(title, genre, author, year, quantityStr, idBookStr)) {
			throw new ServiceException("Incorrect data about book");
		}

		int idBook = Integer.parseInt(idBookStr);
		int quantity = Integer.parseInt(quantityStr);

	
		try {
			bookDAO.addEditBook(title, genre, author, year, quantity, idBook);
		} catch (DAOException e) {
			log.error("Error adding a book to the library", e);
			throw new ServiceException("Error editing book");
		}
	}

	@Override
	public List<Book> getBooklist() throws ServiceException {
		
		List<Book> booklist = null;

		try {
			booklist = bookDAO.getBooklist();
		} catch (DAOException e) {
			log.error("DAO error", e);
			throw new ServiceException(e);
		}

		if (booklist == null) {
			log.error("Booklist not found");
			throw new ServiceException("Booklist not found");
			
		}

		return booklist;
	}

}
