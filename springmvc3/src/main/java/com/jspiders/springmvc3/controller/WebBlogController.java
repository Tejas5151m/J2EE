package com.jspiders.springmvc3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jspiders.springmvc3.dao.UserDAO;
import com.jspiders.springmvc3.dto.UserDTO;
import com.jspiders.springmvc3.dto.WebBlogDTO;
import com.jspiders.springmvc3.service.UserService;
import com.jspiders.springmvc3.service.WebBlogService;
import com.mysql.cj.x.protobuf.MysqlxCursor.Open;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class WebBlogController {

	@Autowired
	private WebBlogDTO blogDTO;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add-blog")
	protected String addBlog() {
		return "add_blog";
	}

	// inserting/adding the blog
	@RequestMapping(value = "/insert-blog")
	private String insertBlog(@RequestParam(name = "title") String title,
			@RequestParam(name = "content") String content, @RequestParam(name = "author") String author,
			HttpSession httpSession, ModelMap modelMap) {

		UserDTO user = (UserDTO) httpSession.getAttribute("user");

		if (user != null) {
			WebBlogDTO blog = WebBlogService.insertBlog(title, content, author, httpSession);
			modelMap.addAttribute("message", "Blog added successefully....!!");
			modelMap.addAttribute("user", user);
			return "home";
		} else {
			return "login";
		}
	}

	// view_blog
	@SuppressWarnings("unused")
	@RequestMapping(value = "/view-blog")
	protected String viewBlog(HttpSession httpSession, ModelMap map) {

		UserDTO user = (UserDTO) httpSession.getAttribute("user");

		int id = user.getId();

		List<WebBlogDTO> blogs = WebBlogService.getMyBlogs(id);

		if (user != null) {
			map.addAttribute("blogs", blogs);
			return "view_blog";
		} else {
			return "login";
		}
	}

	// VIEW ALL BLOG
	@RequestMapping(value = "/all-blog")
	protected String viewAllBlog(HttpSession httpSession, ModelMap modelMap) {

		List<WebBlogDTO> blogs = WebBlogService.getAllBlogs();

		UserDTO user = (UserDTO) httpSession.getAttribute("user");

		if (user != null) {

			if (blogs != null) {
				modelMap.addAttribute("blogs", blogs);
				modelMap.addAttribute("user", user);
			} else {
				modelMap.addAttribute("message", "Something went wrong....!!");
			}
			return "all_blog";

		} else {
			return "login";
		}

	}

	// FETHICNG THR BLOG TO BE UPDATE
	@RequestMapping(value = "update-blog")
	protected String findBlogToBeUpdate(@RequestParam(name = "id") int id, HttpSession httpSession, ModelMap map) {

		WebBlogDTO blog = WebBlogService.findBlogToBeUpdate(id);

		if (blog != null) {
			map.addAttribute("blog", blog);
			return "update_blog";
		} else {
			map.addAttribute("message", "Something went wrong....!!");
			return "view_blog";
		}
	}

	// UPDATING THE BLOG
	@RequestMapping(value = "edit-blog")
	protected String UpdatBlog(@RequestParam(name = "id") int id, @RequestParam(name = "title") String title,
			@RequestParam(name = "content") String content, @RequestParam(name = "author") String author, ModelMap map,
			HttpSession httpSession) {

		System.out.println("controller");

		WebBlogDTO blog = WebBlogService.updateBlog(id, title, content, author);
		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		int userId = user.getId();

		if (blog != null) {

			List<WebBlogDTO> blogs = userService.getAllBlogsOfUser(id);
			map.addAttribute("blogs", blogs);
			map.addAttribute("message", "Blog Updated....!!");
		} else {
			map.addAttribute("message", "Something went wrong....!!");

		}

		return "view_blog";

	}

	// DELETING THE BLOG
	@RequestMapping(value = "delete-blog")
	protected String deleteBlog(@RequestParam(value = "id") int bolgId, HttpSession httpSession, ModelMap map) {

		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		WebBlogDTO blog = WebBlogService.deleteBlog(bolgId, user.getId());

		int userId = user.getId();
		
		if (blog != null) {
			List<WebBlogDTO> blogs = userService.getAllBlogsOfUser(userId);
			map.addAttribute("blogs", blogs);
			map.addAttribute("message", "Blog Updated....!!");
		} else {
			map.addAttribute("message", "Something went wrong....!!");
		}
		return "view_blog";
	}

}
