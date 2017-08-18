package by.htp.library.service.impl;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.service.BookService;
import by.htp.library.service.exception.ServiceException;

public class TestBookServiceImpl {
	private BookService bookService;
	private ConnectionPool connectionPool;
	public TestBookServiceImpl() {
	}
	public TestBookServiceImpl(BookService bookService, ConnectionPool connectionPool) {
		this.bookService = bookService;
		this.connectionPool = connectionPool;

	}
	
	@BeforeClass
	public void initSource() throws ConnectionPoolException {
		try {
			connectionPool.afterPropertiesSet();
		} catch (Exception e) {
			Assert.assertEquals("can not initialize connectionPool", e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterClass
	public void destroySource() throws ConnectionPoolException, IOException {
		try {
			connectionPool.destroy();
		} catch (Exception e) {
			Assert.assertEquals("can not destroy connectionPool", e.getMessage());
			e.printStackTrace();
		}
	}

	@Test(expected = ServiceException.class)
	public void testAddNewBook() throws ServiceException {
		bookService.addNewBook(null, null, null, null, null);
	}

	@Test
	public void testAddEditBook() {
		try {
			bookService.addEditBook(null, "MyAuthor", "MyGenre", "2017", "10", "1");
		} catch (ServiceException e) {
			Assert.assertEquals("Incorrect data about book", e.getMessage());
		}
	}

}
