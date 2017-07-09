package com.example.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;	
	
	private String name;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="guide_id")
	private Guide guide;
	
	@ManyToMany(cascade={CascadeType.PERSIST}, fetch = FetchType.EAGER)
	private Set<Subject> subject = new HashSet<>();
	
	public Student() {}
	public Student(String name, Guide guide) {
		this.name = name;
		this.guide = guide;
	}
	
	public Guide getGuide() {
		return guide;
	}
	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Subject> getSubject() {
		return subject;
	}
	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", guide=" + guide + ", subject=" + subject + "]";
	}
	
	
	
}



