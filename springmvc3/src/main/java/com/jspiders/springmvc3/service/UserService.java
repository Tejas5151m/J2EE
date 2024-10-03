package com.jspiders.springmvc3.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jspiders.springmvc3.dao.UserDAO;
import com.jspiders.springmvc3.dto.Role;
import com.jspiders.springmvc3.dto.UserDTO;
import com.jspiders.springmvc3.dto.WebBlogDTO;

//MY-PROJECT

@Component
public class UserService {

	@Autowired
	private UserDTO userDTO;

	@Autowired
	private UserDAO userDAO;

	// signinig-in
	@SuppressWarnings("unlikely-arg-type")
	public static UserDTO InserUser(String username, String email, long contact, String password, String role) {

		// mine
		if (role.equals("ADMIN")) {
			boolean flag = false;
			ArrayList<UserDTO> users = UserDAO.findAllUsers();
			for (UserDTO user : users) {
				if (user.getRole().equals(Role.ADMIN)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				return null;
			}
		}

		UserDTO user = new UserDTO();
		user.setUserName(username);
		user.setEmail(email);
		user.setMobile(contact);
		user.setPassword(password);
		user.setBlock(false);
		if (role.equals("USER")) {
			user.setRole(Role.USER);
		} else {
			user.setRole(Role.ADMIN);
		}
		user.setBlogs(new ArrayList<WebBlogDTO>());

		try {
			return UserDAO.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Logging in
	public static UserDTO verifyUser(String email, String password) {

//		UserDTO user =  UserDAO.verifyLofin(email, password);

		try {
			return UserDAO.verifyLofin(email, password);
		} catch (Exception e) {
			return null;
		}

	}

	public static UserDTO updateUser(int id, String username, String email, String password, long contact) {

		try {
			return UserDAO.updateUser(id, username, email, password, contact);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static UserDTO deleteUser(int id) {

		try {
			return UserDAO.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static UserDTO findSingleUser(int userId) {

		try {
			return UserDAO.findSingleUser(userId);
		} catch (Exception e) {
			return null;
		}

	}

//SHOW ALL USRS TO ADMIN
	public static List<UserDTO> showAllUsers() {

		try {
			return UserDAO.showAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

//BLOCKING THE USER
	public static UserDTO blockUser(int userId) {

		try {
			return UserDAO.blockUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

//UBLOCKING USER
	public static UserDTO unBlockUser(int id) {
		
		try {
			return UserDAO.unBlockUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
//finding the all blogs of user;
	public List<WebBlogDTO> getAllBlogsOfUser(int id) {
		
		try {
			return userDAO.findAllBlogsOfUser(id);
		} catch (Exception e) {
			return null;
		}
		
	}

}
