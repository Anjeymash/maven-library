package by.htp.library.service;

import by.htp.library.service.exception.ServiceException;

public interface InitializationService {
	void initialization() throws ServiceException;
	void destroy() throws ServiceException;
}
