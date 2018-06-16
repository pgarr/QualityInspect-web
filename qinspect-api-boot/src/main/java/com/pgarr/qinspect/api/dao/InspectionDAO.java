package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionDao extends JpaRepository<Inspection, Long> {

}
