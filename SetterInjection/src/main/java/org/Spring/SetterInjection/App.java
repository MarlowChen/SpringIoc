package org.Spring.SetterInjection;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = 
	              new ClassPathXmlApplicationContext("Beans.xml");

	      PersonalInfo obj = (PersonalInfo) context.getBean("personalInfo");

	      
	       System.out.println(obj.toString());

	}

}
