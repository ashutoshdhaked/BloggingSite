package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entities.Blogger;

public interface BloggersRpositoryDao extends JpaRepository<Blogger, Integer>{

	public Blogger findByEmail(String email);
	@Query(value = "SELECT * FROM Blogger u WHERE u.email = :email and u.password = :pass", 
			  nativeQuery = true)
	public Blogger findByEmailAndPassword(@Param("email") String email,@Param("pass") String password);
	
}
