package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import com.blog.entities.Blogger;
import com.blog.service.BloggerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class IndexController {
	
	@Autowired
    private BloggerService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/index")
	public String indexpage() {
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/loginsineup")
	public String loginSineUp(Model m) {
		m.addAttribute("blogger",new Blogger());
		 m.addAttribute("message","");
		return "loginsineup";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email")String email , @RequestParam("password") String password,Model m ,HttpSession session) {
		// if user request is normal user then redirecting to normal user and if it is admin then admin page 
		  Blogger blogger = service.getBloggerByEmailAndPassword(email, password);
		  session.setAttribute("blogger", blogger);
		  if(blogger==null) {
			  m.addAttribute("message","fail");
			  m.addAttribute("blogger", new Blogger());
			  return "loginsineup";
		  }
		  else {
			  if(blogger.getRole().equals("AdminUser")) {
				  return "***";
			  }
			  
		  }
		
		return "showblogs";
	}
	
	@PostMapping("/sineup")
	public String sineUp(@Valid @ModelAttribute("blogger")Blogger blogger ,BindingResult result,Model m) {
		if(result.hasErrors()) {
			System.out.println(result);
			 m.addAttribute("message","nothing");
		     m.addAttribute("blogger",blogger);
			return "loginsineup";
		}
		blogger.setRole("NormalUser");
		blogger.setProfileimage("default.jpg"); 
		//blogger.setPassword(this.bCryptPasswordEncoder.encode(blogger.getPassword()));
		try {
		this.service.saveBlogger(blogger);
		}
		catch(Exception e) {
			 m.addAttribute("message","failsineup");
			 m.addAttribute("blogger",blogger);
			return "loginsineup";
		}
		m.addAttribute("message","success");
		return "loginsineup";
	}
	
	
	
	
}
