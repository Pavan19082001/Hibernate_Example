package com.demo.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.dto.UserDetails;

public class UserDetailsApplication {
	
	private static SessionFactory factory;
	private static Session session;
	
	public UserDetailsApplication() {
		factory = new Configuration().configure().buildSessionFactory();
		session = factory.openSession();
	}
	
	public void saveUser(UserDetails user) {
		session.beginTransaction();
		session.save(user);
		session.flush();
		session.getTransaction().commit();
	}
	
	public UserDetails findUserDetails(int userId) {
		UserDetails user = session.find(UserDetails.class, userId);
		return user;
	}
	
	public boolean updateUser(UserDetails user, int userId) {
		UserDetails userdetails = findUserDetails(userId);
		userdetails.setUserId(user.getUserId());
		userdetails.setUserName(user.getUserName());
		
		session.beginTransaction();
		session.save(userdetails);
		session.flush();
		session.getTransaction().commit();
		
		return true;
	}
	
	public boolean deleteUser(int id) {
		UserDetails user = findUserDetails(id);
		session.beginTransaction();
		session.delete(user);
		session.flush();
		session.getTransaction().commit();
		
		return true;
	}
	
	public static void main(String[] args) {
		UserDetails user1 = new UserDetails();
		user1.setUserId(1);
		user1.setUserName("Rohit");
		
		UserDetails user2 = new UserDetails();
		user2.setUserId(2);
		user2.setUserName("Mamitha");
		
		UserDetails user3 = new UserDetails();
		user3.setUserId(3);
		user3.setUserName("Pavan Satish");
		
	    UserDetailsApplication userDetails = new UserDetailsApplication();
	    userDetails.saveUser(user1);
	    userDetails.saveUser(user2);
	    userDetails.saveUser(user3);
	    
	   // System.out.println(userDetails.updateUser(user2, 3));   //changing userId with 2 to user3...
	    System.out.println(userDetails.deleteUser(1));          //removing user with id: 2.
	}

}
