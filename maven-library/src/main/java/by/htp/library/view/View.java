package by.htp.library.view;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.library.controller.Controller;

public final class View {
	public static void main(String[] args) {
		String response = null;

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Controller theController = context.getBean("controller", Controller.class);

		// ##Sign up use
	//	response = theController.executeAction("sign_up Anjey_Anjeymash 12345678");
		//PrintRespnse.out(response);

		// ##Sign in user
		//response = theController.executeAction("sign_in Anjey_Anjeymash 12345678");
		//PrintRespnse.out(response);

		// ##Add new book
		// Example: add_new_book Title Genre Author Year Quantity

		//response = theController.executeAction("add_new_book MyBook Action Vasya_Pupkin 2017 2");
		//PrintRespnse.out(response);

		// ##Add edit book
		// Example: add_edit_book Title Genre Author Year Quantity idBook

		//response = theController.executeAction("add_edit_book MyBook Action Petya_Pupkin 2017 2 15");
		//PrintRespnse.out(response);

		// ##Get booklist

		 response = theController.executeAction("get_booklist ");
		 PrintRespnse.out(response);

		// ##Remove book
		// Example: remove_book idBook

		//response = theController.executeAction("remove_book 10");
		//PrintRespnse.out(response);

		context.close();
	}
}
