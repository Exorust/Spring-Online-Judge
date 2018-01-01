package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Professor extends Person {

    @OneToMany(
            mappedBy = "professor", 
            cascade = CascadeType.ALL, 
            orphanRemoval = true
        )
	private List<Course> myCourses;

	public List<Course> getMyCourses() {
		return myCourses;
	}

	public void setMyCourses(List<Course> myCourses) {
		this.myCourses = myCourses;
	}
}
