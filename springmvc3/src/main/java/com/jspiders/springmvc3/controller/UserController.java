package com.jspiders.springmvc3.controller;

import java.awt.Window;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jspiders.springmvc3.dto.Role;
import com.jspiders.springmvc3.dto.UserDTO;
import com.jspiders.springmvc3.service.UserService;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

//MY-PROJECT

@Controller
public class UserController {

	@Autowired
	private UserService service;

	// signup page
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	protected String getSignup() {
		return "signup";
	}

	// logging in - to home page
	@RequestMapping(value = "/home")
	protected String getLogin(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, HttpSession httpSession, ModelMap map) {

		UserDTO user = UserService.verifyUser(email, password);

		if (user != null) {
			httpSession.setAttribute("user", user);
			httpSession.setMaxInactiveInterval(30 * 24 * 60 * 60);

			// new code start
			map.addAttribute("user", user);
			// new code end
			return "home";
		} else {
			map.addAttribute("message", "Invalid Username or Password...!!");
			return "signup";
		}
	}

	// signing in / adding the user
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	protected String getSignup(@RequestParam(name = "username") String username,
			@RequestParam(name = "contact") long contact, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, @RequestParam(name = "role") String role, ModelMap map) {

//		System.out.println("inside user-controller"); // verification

		UserDTO user = UserService.InserUser(username, email, contact, password, role);

		if (user != null) {
			map.addAttribute("message", "User added successefully....!!");
			return "login";
		} else {
			map.addAttribute("message", "Something went wrong plese try agian...!!");
			return "signup";
		}
	}

	// signin(login )
	@RequestMapping(value = "/login")
	protected String showPage() {

		return "login";

	}

	// loggin-out
	@RequestMapping(value = "/logout")
	protected String logOut(HttpSession httpSession, ModelMap map) {

		httpSession.invalidate();
		return "login";

	}

	// UPDATE -PAGE
	@RequestMapping(value = "/update")
	protected String updatePage(HttpSession httpSession, ModelMap map) {

		UserDTO user = (UserDTO) httpSession.getAttribute("user");

		if (user != null) {
			map.addAttribute("user", user);
			return "update";

		} else {
			return "login";
		}

	}

	// updating the user info
	@RequestMapping(value = "/update-info")
	protected String updateUserInfo(@RequestParam(name = "id") int id, @RequestParam(name = "username") String username,
			@RequestParam(name = "email") String email, @RequestParam(name = "password") String password,
			@RequestParam(name = "contact") long contact, ModelMap map) {

		UserDTO user = UserService.updateUser(id, username, email, password, contact);

		if (user != null) {

			map.addAttribute("message", "User updated successefully...!!!");

		} else {
			map.addAttribute("message", "Something went wrong...!!!");

		}
		return "home";

	}

	@RequestMapping(value = "back-home")
	protected String getToHome(HttpSession httpSession, ModelMap map) {

		UserDTO user = (UserDTO) httpSession.getAttribute("user");

		if (user != null) {
			map.addAttribute("user", user);
			return "home";
		} else {
			return "login";
		}
	}

	// SHOW ALL USERS TO ADMIN PAGE
	@RequestMapping(value = "/all-users")
	protected String showAllUsers(HttpSession httpSession, ModelMap map) {

		@SuppressWarnings("unchecked")
		UserDTO user = (UserDTO) httpSession.getAttribute("user");

		if (user != null) {
			map.addAttribute("user", user);
			List<UserDTO> users = (List<UserDTO>) UserService.showAllUsers();
			if (users.size() > 0) {
				map.addAttribute("users", users);
			} else {
				map.addAttribute("message", "No User Found....!!");
			}

		} else {
			return "login";

		}
		return "all_users";

	}

	// BLOCK USER BY ADMIN
	@RequestMapping(value = "blockUser", method = RequestMethod.GET)
	protected String blokUser(@RequestParam(name = "userId") int id, HttpSession httpSession, ModelMap map) {

		UserDTO user = (UserDTO) httpSession.getAttribute("user");

		UserDTO blockUser = UserService.blockUser(id);

		if (user != null) {
			if (blockUser != null) {
				map.addAttribute("user", user);
				List<UserDTO> users = UserService.showAllUsers();
				map.addAttribute("users", users);

			} else {
				map.addAttribute("message", "No User Found....!!");
			}
			return "all_users";
		} else {
			return "login";
		}

	}

	// UNBLOCK USER BY ADMIN
	@RequestMapping(value = "unblockUser")
	protected String unBlokUser(@RequestParam(name = "userId") int id, HttpSession httpSession, ModelMap map) {

		UserDTO user = (UserDTO) httpSession.getAttribute("user");

		UserDTO unBlokedUser = UserService.unBlockUser(id);

		List<UserDTO> users = UserService.showAllUsers();

		if (user != null) {
			if (unBlokedUser != null) {
				map.addAttribute("user", user);
				map.addAttribute("users", users);

			} else {
				map.addAttribute("message", "Something went wrong....!!");
			}
			return "all_users";
		} else {
			return "login";
		}
	}

	// DELETE-USER FUNCTIONALITY
	@RequestMapping(value = "delete-user")
	protected String deleteUser(HttpSession httpSession, ModelMap modelMap) {

		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		int id = user.getId();
		UserDTO deletedUser = service.deleteUser(id);

		if (deletedUser != null) {
			modelMap.addAttribute("message", "User Deleted....!!");
			return "login";
		} else {
			modelMap.addAttribute("message", "Something went wrong....!!");
			return "home";

		}
	}

}
