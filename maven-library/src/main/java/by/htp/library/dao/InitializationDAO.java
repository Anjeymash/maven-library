package by.htp.library.dao;

import by.htp.library.dao.exception.DAOException;

public interface InitializationDAO {
	void initialization() throws DAOException;
	void destroy() throws DAOException;
}
