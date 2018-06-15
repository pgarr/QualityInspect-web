package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemDao extends CrudRepository<Item, Long> {

}
