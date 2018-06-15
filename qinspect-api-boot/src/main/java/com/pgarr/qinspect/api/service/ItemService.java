package com.pgarr.qinspect.api.service;

import java.util.List;

import com.pgarr.qinspect.api.entity.Item;

public interface ItemService {

    public List<Item> getItems();

    public Item getItem(long id);

    public void saveItem(Item item);

}
