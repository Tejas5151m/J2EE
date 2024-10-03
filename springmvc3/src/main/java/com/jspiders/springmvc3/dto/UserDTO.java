package com.jspiders.springmvc3.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ValueGenerationType;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Generated;

@Entity
@Data
@Table(name = "users")
@Component

public class UserDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = false)
	private String userName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private long mobile;

	@Column(nullable = false, unique = false)
	private String password;

	@Column(nullable = false, unique = false)
	private Role role;
	
	@Column(nullable = false, unique = false)
	private boolean block = false;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<WebBlogDTO> blogs;

}