package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item, Long> {

}
