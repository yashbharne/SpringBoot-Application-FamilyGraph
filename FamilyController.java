package com.family.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.entity.FamilyMember;
import com.family.service.FamilyService;


@RestController
@RequestMapping("/family")
public class FamilyController {

	@Autowired
	private FamilyService familyService;


	
	@GetMapping("/{id}")
	public ResponseEntity<FamilyMember> getFamilyMemberById(@PathVariable Long id) {
		FamilyMember familyMember = familyService.getFamilyMemberById(id);
		if (familyMember == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(familyMember);
	}

	
	@PostMapping
	public ResponseEntity<FamilyMember> createFamilyMember(@RequestBody FamilyMember familyMember) {
		FamilyMember createdMember = familyService.createFamilyMember(familyMember);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<FamilyMember> updateFamilyMember(@PathVariable Long id,
	        @RequestBody FamilyMember updatedFamilyMember) {
	    FamilyMember existingMember = familyService.getFamilyMemberById(id);
	    if (existingMember == null) {
	        return ResponseEntity.notFound().build();
	    }

	
	    existingMember.setName(updatedFamilyMember.getName());

	  
	    for (FamilyMember updatedChild : updatedFamilyMember.getChildren()) {
	      
	        FamilyMember existingChild = existingMember.getChildren().stream()
	                .filter(child -> child.getId().equals(updatedChild.getId()))
	                .findFirst().orElse(null);
	        if (existingChild != null) {
	     
	            existingChild.setName(updatedChild.getName());
	        } else {
	      
	            updatedChild.setParent(existingMember);
	            familyService.createFamilyMember(updatedChild); 
	        }
	    }

	    FamilyMember updatedMember = familyService.updateFamilyMember(id, existingMember);

	    return ResponseEntity.ok(updatedMember);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFamilyMember(@PathVariable Long id) {
		familyService.deleteFamilyMember(id);
		return ResponseEntity.noContent().build();
	}
}
