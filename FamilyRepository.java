package com.family.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.entity.FamilyMember;


@Repository
public interface FamilyRepository extends JpaRepository<FamilyMember, Long> {
    List<FamilyMember> findByParentId(Long parentId);
}
