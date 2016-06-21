package com.excel.web.entity;

public class Student {
	private Long id;
	private String name;
	private String subject;
	private int score;

	public Student() {
		
	}
	
	public Student(Long id, String name, String subject, int score) {
		this.id = id;
		this.name = name;
		this.subject = subject;
		this.score = score;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
