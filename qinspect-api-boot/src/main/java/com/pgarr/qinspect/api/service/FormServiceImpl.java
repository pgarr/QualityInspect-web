package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.FormDao;
import com.pgarr.qinspect.api.dao.ItemDao;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormDao formDAO;

    @Autowired
    private ItemDao itemDAO;

    @Override
    @Transactional
    public List<Form> getForms() {
        return formDAO.findAll();
    }

    @Override
    @Transactional
    public Form getForm(long id) {
        // requires lazy init
    }

    @Override
    @Transactional
    public void saveForm(Form form) {
        formDAO.save(form);
    }

    @Override
    @Transactional
    public void archiveForm(long id) {
        // requires custom method with query
    }

    @Override
    public List<Form> getItemForms(long itemId) {

        // requires custom method with query
    }
}
