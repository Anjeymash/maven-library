package by.htp.library.service.impl;

import org.junit.Test;
import by.htp.library.service.UserService;
import by.htp.library.service.exception.ServiceException;

public class TestUserServiceImpl {
	private UserService userService;

	public TestUserServiceImpl() {
	}

	public TestUserServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Test(expected = NullPointerException.class)
	public void signUp() throws ServiceException {

		userService.signUp("Dylan O'Brien", "12345678");
	}
}
