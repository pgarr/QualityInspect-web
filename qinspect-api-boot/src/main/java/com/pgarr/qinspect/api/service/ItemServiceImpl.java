package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.ItemDao;
import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    @Transactional
    public List<Item> getItems() {
        return itemDao.findAll();
    }

    @Override
    @Transactional
    public Item getItem(long id) {
        return itemDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
    }

    @Override
    @Transactional
    public void saveItem(Item item) {
        if(item!=null)
        itemDao.save(item);
        else throw new NullPointerException("Cannot save Item - it's null!");
    }
}
