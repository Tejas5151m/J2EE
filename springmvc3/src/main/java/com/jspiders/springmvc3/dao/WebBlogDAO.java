package com.jspiders.springmvc3.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import com.jspiders.springmvc3.dto.UserDTO;
import com.jspiders.springmvc3.dto.WebBlogDTO;

public class WebBlogDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	public static void openConnection() {
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

	// adding the blog
	public static WebBlogDTO addBlog(WebBlogDTO blog, int id) {

		openConnection();
		entityTransaction.begin();

		UserDTO user = entityManager.find(UserDTO.class, id);
		List<WebBlogDTO> blogs = user.getBlogs();
		blogs.add(blog);
		user.setBlogs(blogs);

		entityManager.persist(blog);
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();

		return blog;
	}

	// FETHICNG THR BLOG TO BE UPDATE
	public static WebBlogDTO findBlogToBeUpdated(int id) {
		openConnection();
		WebBlogDTO blog = entityManager.find(WebBlogDTO.class, id);
		closeConnection();
		return blog;
	}

	public static WebBlogDTO updateBlog(int id, String title, String content, String author) {

		openConnection();

		WebBlogDTO blog = entityManager.find(WebBlogDTO.class, id);
		blog.setTital(title);
		blog.setContent(content);
		blog.setDate(new Date(System.currentTimeMillis()));
		blog.setAuthor(author);

		entityTransaction.begin();
		entityManager.persist(blog);
		entityTransaction.commit();

		closeConnection();
		System.out.println("Blog updated");
		return blog;

	}

	public static WebBlogDTO deleteBlog(int blogId, int userId) throws Exception {

		openConnection();
		WebBlogDTO blog = entityManager.find(WebBlogDTO.class, blogId);
		UserDTO user = entityManager.find(UserDTO.class, userId);

		List<WebBlogDTO> webBlogs = user.getBlogs();

		WebBlogDTO blogToBeDeleted = null;

		for (WebBlogDTO b : webBlogs) {
			if (b.getId() == blog.getId()) {
				blogToBeDeleted = b;
				break;
			}
		}

		webBlogs.remove(blogToBeDeleted);
		user.setBlogs(webBlogs);

		entityTransaction.begin();
		entityManager.persist(user);
		entityManager.remove(blog);
		entityTransaction.commit();
		closeConnection();

		return blog;

	}

	public static List<WebBlogDTO> getAllBlog() {

		openConnection();
		Query query = entityManager.createQuery("SELECT b FROM WebBlogDTO b");

		List<WebBlogDTO> blogs = query.getResultList();

		closeConnection();

		return blogs;

	}

	public static List<WebBlogDTO> getMyBlogs(int id) {

		openConnection();
		UserDTO user = entityManager.find(UserDTO.class, id);
		List<WebBlogDTO> blogs = user.getBlogs();
		closeConnection();
		return blogs;

	}

}
