package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.ItemDao;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDAO;

    @Override
    @Transactional
    public List<Item> getItems() {

        Iterable<Item> iterable = itemDAO.findAll();

        List<Item> items = new ArrayList<>();
        iterable.forEach(items::add);

        return items;
    }

    @Override
    @Transactional
    public Item getItem(long id) {
        return itemDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
    }

    @Override
    @Transactional
    public void saveItem(Item item) {
        itemDAO.save(item);
    }
}
