package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.User;
import by.htp.library.dao.ColumnLabel;
import by.htp.library.dao.SQLCommand;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.connection.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import by.htp.library.dao.exception.DAOException;

public class UserDAOImpl implements UserDAO {
private static final Logger log = LogManager.getRootLogger();
private ConnectionPool pool;
public UserDAOImpl() {}
public UserDAOImpl(ConnectionPool pool) {
	this.pool = pool;
}

	@Override
	public User signIn(String login, int password) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		User user = null;
		
		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.SELECT_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setInt(2, password);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(ColumnLabel.USER_ID));
				user.setLogin(resultSet.getString(ColumnLabel.USER_LOGIN));
				user.setPassword(resultSet.getInt(ColumnLabel.USER_PASSWORD));
			}
		} catch (ConnectionPoolException e) {
			log.error("fail in ConnectionPool", e);
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			log.error("Error executing the query 'select_user_id_by_login_password'", e);
			throw new DAOException("Error executing the query 'select_user_id_by_login_password'", e);
		}finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return user;
	
	}

	@Override
	public void signUp(String login, int password) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.INSERT_USER);
			preparedStatement.setString(1, login);
			preparedStatement.setInt(2, password);		
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			log.error("fail in ConnectionPool", e);
			throw new DAOException("There was a problem connecting to the database", e);
			
		} catch (SQLException e) {
			log.error("Error executing the query 'insert_user'", e);
			throw new DAOException("Error executing the query 'insert_user'", e);
		}finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

}
