package com.family.service;

import java.util.List;

import com.family.entity.FamilyMember;


public interface FamilyService {
    FamilyMember getFamilyMemberById(Long id);
    FamilyMember createFamilyMember(FamilyMember familyMember);
    FamilyMember updateFamilyMember(Long id, FamilyMember familyMember);
    void deleteFamilyMember(Long id);
	
}
