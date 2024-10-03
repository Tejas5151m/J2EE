package Testing;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.jspiders.springmvc3.dto.UserDTO;
import com.jspiders.springmvc3.dto.WebBlogDTO;
import com.jspiders.springmvc3.service.UserService;

public class db_Testing {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

//	public static UserDTO verifyLofin(String email, String password) {
//		
//		openConnection();
//		Query query = entityManager.createQuery("SELECT u FROM UserDTO u where u.email=?1 AND u.password=?2");
//		
//		query.setParameter(1, email);
//		query.setParameter(2, password);
//		
//		UserDTO user =(UserDTO) query.getSingleResult();
//		closeConnection();
//		return user;
//		
//		
//	}

//	public static UserDTO updateUser(int id, String username, String email, String password, long contact)
//			throws SQLIntegrityConstraintViolationException {
//
//		openConnection();
//
//		UserDTO user = entityManager.find(UserDTO.class, id);
//		user.setUserName(username);
//		user.setEmail(email);
//		user.setPassword(password);
//		user.setMobile(contact);
//
//		entityTransaction.begin();
//		entityManager.persist(user);
//		entityTransaction.commit();
//
//		closeConnection();
//		return user;
//	}

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

	public static void main(String[] args) {
//		UserDTO dto= verifyLofin("tejas@gmail.com", "tejas123");
//		System.out.println(dto.getUserName());

//		try {
//			System.out.println("Start");
//			updateUser(2, "sai123@gmail.com", "sai", "sai1234", 1234567899);
//			System.out.println("Update successfull");
//		} catch (SQLIntegrityConstraintViolationException e) {
//			e.printStackTrace();
//		}

//		findblog(1);


	}


//	public static void findblog(int id) {
//		openConnection();
//		WebBlogDTO blog = entityManager.find(WebBlogDTO.class, id);
//		System.out.println(blog.getAuthor());
//		blog.setAuthor("Sayloo");
//
//		entityTransaction.begin();
//		entityManager.persist(blog);
//		entityTransaction.commit();
//		closeConnection();
//
//		openConnection();
//		WebBlogDTO blog1 = entityManager.find(WebBlogDTO.class, id);
//		System.out.println(blog1.getAuthor());
//		closeConnection();
//
//	}

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

}
