package com.pgarr.qualityinspect.service;

import java.util.List;

import com.pgarr.qualityinspect.entity.Item;

public interface ItemService {

	public List<Item> getItems();

	public Item getItem(int itemId);

	public void saveItem(Item item);

}
