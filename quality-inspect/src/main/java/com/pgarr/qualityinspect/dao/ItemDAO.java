package com.pgarr.qualityinspect.dao;

import java.util.List;

import com.pgarr.qualityinspect.entity.Item;

public interface ItemDAO {

	List<Item> getItems();

	Item getItem(int itemId);

	void saveItem(Item item);

}
