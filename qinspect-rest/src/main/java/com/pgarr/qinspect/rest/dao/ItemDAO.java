package com.pgarr.qinspect.rest.dao;

import java.util.List;

import com.pgarr.qinspect.rest.entity.Item;

public interface ItemDAO {

	public List<Item> getItems();

	public Item getItem(int id);

	public Item getItemWithForms(int id);

	public void saveItem(Item item);

}