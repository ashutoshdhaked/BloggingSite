package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.dao.BlogRepositoryDao;
import com.blog.entities.Blogger;
import com.blog.entities.Blogs;


@Service
public class BlogService {
	
	@Autowired
	private BlogRepositoryDao dao;
	
	public Blogs saveBlog(Blogs blog,Blogger b) {
		blog.setBlogger(b);
	    Blogs savedblog = this.dao.save(blog);	
	   return savedblog;
		
	}
	
	public Page<Blogs> getAllBlogsInPage(Pageable p){
		Page<Blogs> blogsInPage = this.dao.findAll(p);
		return blogsInPage;
	}
	
	public List<Blogs> getBlogByBlogger( int bloggerId){
		  List<Blogs> list = this.dao.findAllByBloggerBid(bloggerId);		
	    	return list;
	}
		
	public List<Blogs> getAllBlogs(){
		List<Blogs> list = this.dao.findAll();
		return list;
	} 
	
	public Blogs getBlogById(int id) {
		 Optional<Blogs> optional = this.dao.findById(id);
		 Blogs blog = optional.get();
		return blog;
	}
	
	public Blogs updateBlog(Blogs b, int id) {
		 Optional<Blogs> optional = this.dao.findById(id);
		 Blogs blog = optional.get();
		 blog.setText(b.getText());
		 blog.setCategory(b.getCategory());
		 blog.setTime(b.getTime());
		 blog.setTitle(b.getTitle());
		 blog.setImageorvideo(b.getImageorvideo());
		 Blogs b2 = this.dao.save(blog);
		return b2;
	}
	
	public String updateLike(int id) {
		Optional<Blogs> optional = this.dao.findById(id);
		 Blogs blog = optional.get();
		 String likes = blog.getLikes();
		  int i = Integer.parseInt(likes);
		  i++;
		  likes = ""+i;
		  blog.setLikes(likes);
		  this.dao.save(blog);
		  return likes;
		
	}
	
	public void deleteBlog(int id) {
		this.dao.deleteById(id);
		
	}
	

}
