package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.ColumnLabel;
import by.htp.library.dao.SQLCommand;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;

public class BookDAOImpl implements BookDAO {
	private static final Logger log = LogManager.getRootLogger();
	private ConnectionPool pool;
	public BookDAOImpl() {}
	public BookDAOImpl(ConnectionPool pool) {
		this.pool = pool;}
	
	@Override
	public void addNewBook(String title, String author, String genre, String year, int quantity) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.INSERT_BOOK);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);		
			preparedStatement.setString(3, genre);	
			preparedStatement.setString(4, year);	
			preparedStatement.setInt(5, quantity);	
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			log.error("fail in ConnectionPool", e);
			throw new DAOException("There was a problem connecting to the database", e);
			
		} catch (SQLException e) {
			log.error("Error executing the query 'inser_book'", e);
			throw new DAOException("Error executing the query 'inser_book'", e);
		}finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public void addEditBook(String title, String genre, String author, String year, int quantity, int idBook) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_BOOK);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, author);
			preparedStatement.setString(3, genre);
			preparedStatement.setString(4, year);
			preparedStatement.setInt(5, quantity);
			preparedStatement.setInt(6, idBook);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			log.error("fail in ConnectionPool", e);
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			log.error("Error executing the query 'update_book'", e);
			throw new DAOException("Error executing the query 'update_book'", e);
		}finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public List<Book> getBooklist() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Book> booklist = null;
		
		try {
			connection = pool.take();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_BOOK);
			
			booklist = new ArrayList<Book>();;
			Book book = null;
			
			while(resultSet.next()){
				book = new Book();
				book.setId(resultSet.getInt(ColumnLabel.BOOK_ID));
				book.setTitle(resultSet.getString(ColumnLabel.BOOK_TITLE));
				book.setAuthor(resultSet.getString(ColumnLabel.BOOK_AUTHOR));
				book.setGenre(resultSet.getString(ColumnLabel.BOOK_GENRE));
				book.setYear(resultSet.getString(ColumnLabel.BOOK_YEAR));
				book.setQuantity(resultSet.getInt(ColumnLabel.BOOK_QUANTITY));
				book.setStatus(resultSet.getBoolean(ColumnLabel.BOOK_STATUS));
				booklist.add(book);
			}
		
		} catch (ConnectionPoolException e) {
			log.error("fail in ConnectionPool", e);
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			log.error("Error executing the query 'select_book'", e);
			throw new DAOException("Error executing the query 'select_book'", e);
		}finally {
			pool.closeConnection(connection, statement, resultSet);
		}		
		
		return booklist;
	}



}
