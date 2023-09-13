package com.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Blogs {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int blid;
	@NotBlank
	@Column(unique = true)
    private String title;
	@NotBlank
	@Column(length=4000)
	private StringBuilder text;
	private String likes;
	@NotBlank
	private String time;
	@NotBlank
	private String imageorvideo;
	@NotBlank
	private String category;
	@ManyToOne
    private Blogger blogger;
	
	public Blogs(@NotBlank String title, @NotBlank StringBuilder text, String likes, @NotBlank String time,
			@NotBlank String imageorvideo, @NotBlank String category, Blogger blogger) {
		super();
		this.title = title;
		this.text = text;
		this.likes = likes;
		this.time = time;
		this.imageorvideo = imageorvideo;
		this.category = category;
		this.blogger = blogger;
	}
	
	public Blogs() {
		super();
	}

	public int getBlid() {
		return blid;
	}

	public void setBlid(int blid) {
		this.blid = blid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StringBuilder getText() {
		return text;
	}

	public void setText(StringBuilder text) {
		this.text = text;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getImageorvideo() {
		return imageorvideo;
	}

	public void setImageorvideo(String imageorvideo) {
		this.imageorvideo = imageorvideo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}
		
}
