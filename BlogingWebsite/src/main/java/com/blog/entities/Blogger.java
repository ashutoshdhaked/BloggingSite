package com.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



@Entity
public class Blogger {
     
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int bid;
	@NotBlank(message = "name can not be empty!!")
	@Size(min=4,max=30,message = "your name must be between 4-30 character !!")
	private String name;
	@Size(min=8,max=20,message = "username must be between 8-20 character !!")
	private String username;
	@Column(unique = true,nullable = false)
    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "please enter a valid email")
	private String email;
	@NotNull(message = "password can not be empty!!")
	@Pattern(regexp ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",
    message = "password must contain atleast 8-30 character , must contain special character "
	+ ", must contain Uppecase and lowercase character , must contain number character")
	private String password;
	private String profileimage;
	private String role;
	@OneToMany(cascade = CascadeType.ALL ,fetch = FetchType.EAGER , mappedBy = "blogger")
	private List<Blogs> blogs = new ArrayList<>();
	
	public Blogger(String name,String username, String email, String password,String profileimage, List<Blogs> blogs,String role) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.profileimage = profileimage;
		this.blogs = blogs;
		this.role=role;
	}

	public Blogger() {
		super();
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}

	public List<Blogs> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blogs> blogs) {
		this.blogs = blogs;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
