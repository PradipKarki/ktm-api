package com.ktm.reference.repository;

import com.ktm.reference.model.ReferenceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceTypeRepository extends JpaRepository<ReferenceType, String> {
}
