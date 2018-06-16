package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Form;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FormDao extends CrudRepository<Form, Long> {

    List<Form> findByArchivedFalse();

    List<Form> findByArchivedFalseAndItem_Id(long itemId);
}
