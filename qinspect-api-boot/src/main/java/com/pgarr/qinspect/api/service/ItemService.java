package com.pgarr.qinspect.api.service;

import java.util.List;

import com.pgarr.qinspect.api.entity.Item;

public interface ItemService {

    List<Item> getItems();

    Item getItem(long id);

    void saveItem(Item item);

}
