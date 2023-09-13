package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.BloggersRpositoryDao;
import com.blog.entities.Blogger;


@Service
public class BloggerService {
	
	@Autowired
	private BloggersRpositoryDao dao;
	
	public Blogger saveBlogger(Blogger blogger) {
		Blogger b = this.dao.save(blogger);		
		return b;
	}
	 
	public List<Blogger> getAllBlogger() {
		List<Blogger> list = this.dao.findAll();
		return list;
	}
	
	public Blogger getBloggerByEmailAndPassword(String email,String password) {
		Blogger blogger = this.dao.findByEmailAndPassword(email, password);
		return blogger;
	}
	
	public Blogger getBloggerById(int id) {
		Optional<Blogger> optional = this.dao.findById(id);
		Blogger blogger =optional.get();
		return blogger;
	}
	
	public Blogger updateBlogger(Blogger blogger , int id) {
		Optional<Blogger> b = this.dao.findById(id);
		Blogger b1 = b.get();
		b1.setName(blogger.getName());
		b1.setEmail(blogger.getEmail());
		b1.setUsername(blogger.getUsername());
		b1.setProfileimage(blogger.getProfileimage());		
		Blogger blogger2 = this.dao.save(b1);
		return blogger2;
	}
	

}
