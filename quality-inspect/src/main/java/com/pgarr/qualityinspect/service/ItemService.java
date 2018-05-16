package com.pgarr.qualityinspect.service;

import java.util.List;

import com.pgarr.qualityinspect.entity.Item;

public interface ItemService {

	List<Item> getItems();

	Item getItem(int itemId);

	void saveItem(Item item);

}
