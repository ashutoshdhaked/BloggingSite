package com.blog.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.entities.Blogs;

public interface BlogRepositoryDao extends JpaRepository<Blogs, Integer>{

	//@Query(value = "SELECT * FROM Blogs b WHERE b.blogger =:blogger",nativeQuery = true)
//	public List<Blogs> findAllBlogsByBlogger(@Param("blogger") Blogger blogger  );
	
	 public Page<Blogs> findAll(Pageable pageable);
	public  List<Blogs> findAllByBloggerBid(int bloggerBid);
	
}
