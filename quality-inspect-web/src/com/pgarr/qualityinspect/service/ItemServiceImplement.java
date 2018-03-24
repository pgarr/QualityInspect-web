package com.pgarr.qualityinspect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pgarr.qualityinspect.dao.ItemDAO;
import com.pgarr.qualityinspect.entity.Item;

@Service
public class ItemServiceImplement implements ItemService {

	@Autowired
	private ItemDAO itemDAO;

	@Override
	@Transactional
	public List<Item> getItems() {

		return itemDAO.getItems();
	}

}
