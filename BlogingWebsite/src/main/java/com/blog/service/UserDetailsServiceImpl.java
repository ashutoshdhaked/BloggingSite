package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.config.CustomUserDetails;
import com.blog.dao.BloggersRpositoryDao;
import com.blog.entities.Blogger;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private BloggersRpositoryDao bloggersRpositoryDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Blogger blogger = bloggersRpositoryDao.findByEmail(username);
		CustomUserDetails customUserDetails = new CustomUserDetails(blogger);
		return customUserDetails;
	}

}
