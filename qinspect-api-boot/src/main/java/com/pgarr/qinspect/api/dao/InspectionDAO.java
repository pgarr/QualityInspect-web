package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Inspection;
import org.springframework.data.repository.CrudRepository;

public interface InspectionDao extends CrudRepository<Inspection, Long> {

}
