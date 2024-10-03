package com.jspiders.springmvc3.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jspiders.springmvc3.dao.UserDAO;
import com.jspiders.springmvc3.dao.WebBlogDAO;
import com.jspiders.springmvc3.dto.UserDTO;
import com.jspiders.springmvc3.dto.WebBlogDTO;

@Component
public class WebBlogService {

	@Autowired
	private WebBlogDTO blogDTO;

	// inserting/adding the blog
	public static WebBlogDTO insertBlog(String title, String content, String author, HttpSession httpSession) {

		System.out.println("Service");// verification

		WebBlogDTO blog = new WebBlogDTO();
		blog.setTital(title);
		blog.setContent(content);
		blog.setDate(new Date(System.currentTimeMillis()));
		blog.setAuthor(author);

		UserDTO user = (UserDTO) httpSession.getAttribute("user");
		int id = -1;
		try {
			id = user.getId();
		} catch (Exception e) {
			return null;
		}

		WebBlogDAO.addBlog(blog, id);

		try {
			return WebBlogDAO.addBlog(blog, id);
		} catch (Exception e) {
			return null;
		}
	}

	
	
	//FETHICNG THR BLOG TO BE UPDATE
	public static WebBlogDTO findBlogToBeUpdate(int id) {

		try {
			return WebBlogDAO.findBlogToBeUpdated(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}


	//updating the blog
	public static WebBlogDTO updateBlog(int id, String title, String content, String author) {

		
		try {
			return WebBlogDAO.updateBlog(id, title, content, author);

		} catch (Exception e) {
			return null;
		}
		
	}


//DELETINGHTE SINGLE BLOG
	public static WebBlogDTO deleteBlog(int blogId, int userId ) {

		try {
			return WebBlogDAO.deleteBlog(blogId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}


//FINDING THE ALL BLOGS
	public static List<WebBlogDTO> getAllBlogs() {

		try {
			return WebBlogDAO.getAllBlog();
		} catch (Exception e) {
			return null;
		}
	}


// FINDING SINGLE USER BLOGS
	public static List<WebBlogDTO> getMyBlogs(int id) {

		try {
			return WebBlogDAO.getMyBlogs(id);
		} catch (Exception e) {
			return null;
		}
		
	}

	

}
