package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.ItemDao;
import com.pgarr.qinspect.api.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDAO;

    @Override
    @Transactional
    public List<Item> getItems() {
        return itemDAO.findAll();
    }

    @Override
    @Transactional
    public Item getItem(long id) {
        // requires lazy init
    }

    @Override
    @Transactional
    public void saveItem(Item item) {
        itemDAO.save(item);
    }
}
