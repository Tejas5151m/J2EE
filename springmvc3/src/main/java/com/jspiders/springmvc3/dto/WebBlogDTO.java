package com.jspiders.springmvc3.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Entity
@Table(name = "blog")
@Data
public class WebBlogDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	
	@Column(nullable = false, unique = false)
	private String tital;
	
	@Column(nullable = false, unique = false)
	private String content;
	
	@Column(nullable = false, unique = false)
	private Date date;
	
	@Column(nullable = false, unique = false)
	private String author;
	
}
