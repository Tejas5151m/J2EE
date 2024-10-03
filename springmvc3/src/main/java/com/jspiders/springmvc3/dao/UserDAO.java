package com.jspiders.springmvc3.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Component;

import com.jspiders.springmvc3.dto.UserDTO;
import com.jspiders.springmvc3.dto.WebBlogDTO;
import com.jspiders.springmvc3.service.UserService;

@Component
public class UserDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
//	private static Query query; 

	public static void openConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		entityManagerFactory = Persistence.createEntityManagerFactory("springmvc");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	public static void closeConnection() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
		if (entityManager != null) {
			entityManager.close();
		}
		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}

//	// inserting the user / signing in
//	public static UserDTO insert(UserDTO user) {
//
//		System.out.println("Inside user-Dao"); // verification
//		openConnection();
//		System.out.println(user.getUserName());
//		entityTransaction.begin();
//		entityManager.persist(user);
//		entityTransaction.commit();
//		closeConnection();
//
//		return user;
//
//	}

	public static ArrayList<UserDTO> findAllUsers() {

		openConnection();
		Query query = entityManager.createQuery("SELECT u FROM UserDTO u");
		ArrayList<UserDTO> users = (ArrayList<UserDTO>) query.getResultList();
		return users;

	}

//	 updating the user
	public static UserDTO updateUser(int id, String username, String email, String password, long contact)
			throws SQLIntegrityConstraintViolationException {

		openConnection();

		UserDTO user = entityManager.find(UserDTO.class, id);
		user.setUserName(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setMobile(contact);

		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();

		closeConnection();

		return user;
	}

	// logging in
	public static UserDTO verifyLofin(String email, String password) {

		openConnection();
		Query query = entityManager.createQuery("SELECT u FROM UserDTO u where u.email=?1 AND u.password=?2");

		query.setParameter(1, email);
		query.setParameter(2, password);

		UserDTO user = (UserDTO) query.getSingleResult();
		closeConnection();
		return user;
	}

	public static UserDTO deleteUser(int id) throws IllegalIdentifierException, TransactionRequiredException {

		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		entityTransaction.begin();
		entityManager.remove(user);
		entityTransaction.commit();
		entityManager.close();
		closeConnection();
		
		return user;

	}

	public static UserDTO addUser(UserDTO user) {
		openConnection();
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}

	public static UserDTO findSingleUser(int userId) {

		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, userId);
		closeConnection();

		return user;
	}

	public static List<UserDTO> showAllUsers() {

		openConnection();
        Query query = entityManager.createQuery("SELECT u FROM UserDTO u");
		@SuppressWarnings("unchecked")
		List<UserDTO> users =  query.getResultList();
		closeConnection();
		return users;
		
	}

	//BLOCKING USER
	public static UserDTO blockUser(int userId) {
		
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, userId);
		user.setBlock(true);
		
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		
		closeConnection();
		
		return user;
	}

	public static UserDTO unBlockUser(int id) {

		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		user.setBlock(false);
		
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		
		closeConnection();
		
		return user;
		
	}

	public List<WebBlogDTO> findAllBlogsOfUser(int id) {
		openConnection();
		UserDTO user = entityManager.find(UserDTO.class,id);
		List<WebBlogDTO> blogs = user.getBlogs();
		return blogs;
		
	}

}
