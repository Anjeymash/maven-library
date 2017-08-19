package by.htp.library.service.impl;

import org.junit.Test;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class TestUserServiceImpl {
/*	���������������� ������������ �� ����������, �.�. �� �� ���������������� ConnectionPool
 	�������������� ����� ����� Connection �������� NullPointerException.
*/
	@Test (expected = NullPointerException.class)
	public void signUp() throws ServiceException{
		
		UserService userService = new UserServiceImpl();
		userService.signUp("Dylan O'Brien", "12345678");
	}
}