package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Inspection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InspectionDao extends JpaRepository<Inspection, Long> {

    @Override
    @EntityGraph(attributePaths = {"results"})
    Optional<Inspection> findById(Long aLong);
}
