package com.family.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.family.entity.FamilyMember;
import com.family.repository.FamilyRepository;


@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public FamilyMember getFamilyMemberById(Long id) {
        return familyRepository.findById(id).orElse(null);
    }

    @Override
    public FamilyMember createFamilyMember(FamilyMember familyMember) {
        return familyRepository.save(familyMember);
    }

    @Override
    public FamilyMember updateFamilyMember(Long id, FamilyMember updatedMember) {
        FamilyMember existingMember = familyRepository.findById(id).orElse(null);
        if (existingMember != null) {
            existingMember.setName(updatedMember.getName());
            // Add logic for updating children, parent, etc. if necessary
            return familyRepository.save(existingMember);
        }
        return null;
    }

    @Override
    public void deleteFamilyMember(Long id) {
        familyRepository.deleteById(id);
    }
    
    public void exportFamilyMembersToCSV(String filePath) {
        List<FamilyMember> familyMembers = familyRepository.findAll();
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("id,name,parent_id\n");
            for (FamilyMember member : familyMembers) {
                writer.append(member.getId() + "," + member.getName() + "," +
                        (member.getParent() != null ? member.getParent().getId() : "") + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}
