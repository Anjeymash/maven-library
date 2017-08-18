package by.htp.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.bean.User;
import by.htp.library.dao.UserDAO;
import by.htp.library.dao.exception.DAOException;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;
import by.htp.library.service.validation.ValidationData;

public class UserServiceImpl implements UserService {
private static final Logger log = LogManager.getRootLogger();
public UserDAO userDAO;
public UserServiceImpl() {}
public UserServiceImpl(UserDAO userDAO) {
	this.userDAO = userDAO;
}

	@Override
	public void signIn(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException("Iccorrent user's login or password");
		}
		
		
		try {
			
			User user = userDAO.signIn(login, password.hashCode());
			if(user == null){
				throw new ServiceException("User is not found");
			}
		} catch (DAOException e) {
			log.error("fail in DAOImpl", e);
			throw new ServiceException("Error sign in", e);
		}
	}

	@Override
	public void signUp(String login, String password) throws ServiceException {
		if(!ValidationData.validUser(login, password)){
			throw new ServiceException("Icorrent user's login or password");
		}
		
		
		
		//Attention String_paswword convert to int_password(HashCode)
		try {
			userDAO.signUp(login, password.hashCode());
		} catch (DAOException e) {
			log.error("fail in DAOImpl", e);
			throw new ServiceException("Error sign up", e);
		}
	}

}
