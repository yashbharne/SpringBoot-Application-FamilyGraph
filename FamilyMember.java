package com.family.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class FamilyMember {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;

 @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
 @JsonManagedReference
 private List<FamilyMember> children;

 @ManyToOne
 @JoinColumn(name = "parent_id")
 @JsonBackReference
 private FamilyMember parent;



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

	public List<FamilyMember> getChildren() {
		return children;
	}

	public void setChildren(List<FamilyMember> children) {
		this.children = children;
	}

	public FamilyMember getParent() {
		return parent;
	}

	public void setParent(FamilyMember parent) {
		this.parent = parent;
	}




 
}
