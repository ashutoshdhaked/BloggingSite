package com.blog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entities.Blogger;
import com.blog.entities.Blogs;
import com.blog.helper.FileHelper;
import com.blog.service.BlogService;
import com.blog.service.BloggerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class NormalUserController {
	
	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/showblogs/{page}")
	public String  loggedUser(@PathVariable("page")int page,Model m) {

		 Pageable p = PageRequest.of(page, 6);
          Page<Blogs> list = this.blogService.getAllBlogsInPage(p);
          
          System.out.println("******************************************************************");
          System.out.println("list : "+list);
           m.addAttribute("msg", "yes");
           m.addAttribute("list", list);
           m.addAttribute("currentPage", page);
           m.addAttribute("totalpage", list.getTotalPages());
		return "showblogs";		
	}
	
	@GetMapping("/userblogs")
	public String userBlogs(HttpSession session,Model m) {
		Blogger blogger = (Blogger)session.getAttribute("blogger");
	    List<Blogs> list = this.blogService.getBlogByBlogger(blogger.getBid());
	    if(list.isEmpty()) {
	    	m.addAttribute("message","empty");
	    }
	     m.addAttribute("message"," ");
	     m.addAttribute("list",list);
		return "userblogs";		
	}
	
	@GetMapping("/createblog")
	public String createBlog(Model m,HttpSession session) {	
		try {
		m.addAttribute("message"," ");
		Blogger blogger = (Blogger)session.getAttribute("blogger");
		int id = blogger.getBid();
		m.addAttribute("bloggerId", id);
		return "createblog";	
		}
		catch(Exception e) {
			m.addAttribute("message","m");
			return "createblog";
		}
	}
	@GetMapping("/profile")
	public String profile(HttpSession session,Model model) {
		Blogger blogger = (Blogger)session.getAttribute("blogger");
		    List<Blogs> list = this.blogService.getBlogByBlogger(blogger.getBid());
		model.addAttribute("blogger", blogger);
		model.addAttribute("list", list);
		model.addAttribute("msg", " ");
		return "profile";		
	}
	
	@PostMapping("/updateimage")
	public String updateprofile(@RequestParam("bloggerid") int bid,@RequestParam("file") MultipartFile file,Model model,HttpSession session) {
		Blogger blogger1 = (Blogger)session.getAttribute("blogger");
	    List<Blogs> list = this.blogService.getBlogByBlogger(blogger1.getBid());
		try {
			  if(file.isEmpty()) {
				  model.addAttribute("blogger", blogger1);
				  model.addAttribute("list", list);
				  model.addAttribute("msg", "notimage");
				  return "profile";
			  }
			  else {
			  String fileName = FileHelper.generateRandomFileName(file.getOriginalFilename());
			  Blogger blogger = this.bloggerService.getBloggerById(bid);
		      FileHelper.uploadfile(file, fileName);
              blogger.setProfileimage(fileName);
              this.bloggerService.saveBlogger(blogger); 
              
               model.addAttribute("blogger",new Blogger());
     		   model.addAttribute("message","logout");
              session.removeAttribute("blogger");
		      return "loginsineup";
			  }
		  }
		  catch(Exception e) {
			  model.addAttribute("blogger", blogger1);
			  model.addAttribute("list", list);
			  model.addAttribute("msg", "fail");
			  return "profile";
		  }
	}
	
	@GetMapping("/readblog")
	public String readblog(@RequestParam("bid") int bid, Model m,HttpSession session) {	
		  Blogger blogger = (Blogger)session.getAttribute("blogger");
		  Blogs blog = this.blogService.getBlogById(bid);
		  m.addAttribute("blog", blog);
		return "readblog";		
	}
	
	@GetMapping("/logout")
	public String logedOut(Model m,HttpSession session) {
		 m.addAttribute("blogger",new Blogger());
		 m.addAttribute("message","logout");
		 session.removeAttribute("blogger");
		return "loginsineup";
	}
	
	
	
	@PostMapping("/savedata")
	public String saveblog( @RequestParam("file") MultipartFile file,
			    @RequestParam("title") String title,
			    @RequestParam("category") String category,
			    @RequestParam("text") StringBuilder text,
			    @RequestParam("bid") int id,Model m) {
		 try {
		        String fileName = FileHelper.generateRandomFileName(file.getOriginalFilename());
		        FileHelper.uploadfile(file, fileName);
		        Blogger b = this.bloggerService.getBloggerById(id); 
		        Blogs blogs = new Blogs();
		        blogs.setTitle(title);
		        blogs.setCategory(category);
		        blogs.setText(text);
		        blogs.setTime(""+new Date());
		        blogs.setLikes("120");
		        blogs.setImageorvideo(fileName);
		        Blogs saveBlog = this.blogService.saveBlog(blogs, b); 
		        if(saveBlog==null) {
		        	m.addAttribute("message","m");
		        	return "createblog";
		        }
		 }
		 catch(Exception e) {
			 m.addAttribute("message","m");
	        	return "createblog";
		 }
		        
		return "userblogs";
	}
	
	
	@GetMapping("/adminuser")
	public String adminLogin() {
		
		return "***";		
	}
	
	

}
