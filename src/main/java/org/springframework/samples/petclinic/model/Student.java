package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student extends Person{
	
    @OneToMany(
            cascade = CascadeType.ALL, 
            orphanRemoval = true
        )
	private List<Course> myCourses;
	
    @OneToMany(
            mappedBy = "student", 
            cascade = CascadeType.ALL, 
            orphanRemoval = true
        )
	private List<Submission> mySubmissions;

	public List<Course> getMyCourses() {
		return myCourses;
	}

	public void setMyCourses(List<Course> myCourses) {
		this.myCourses = myCourses;
	}

	public List<Submission> getMySubmissions() {
		return mySubmissions;
	}

	public void setMySubmissions(List<Submission> mySubmissions) {
		this.mySubmissions = mySubmissions;
	}
}
