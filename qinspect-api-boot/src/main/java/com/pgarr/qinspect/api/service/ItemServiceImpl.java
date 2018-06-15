package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.ItemDAO;
import com.pgarr.qinspect.api.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    @Override
    @Transactional
    public List<Item> getItems() {
        return itemDAO.getItems();
    }

    @Override
    @Transactional
    public Item getItem(int id) {
        return itemDAO.getItem(id);
    }

    @Override
    @Transactional
    public void saveItem(Item item) {
        itemDAO.saveItem(item);
    }
}
