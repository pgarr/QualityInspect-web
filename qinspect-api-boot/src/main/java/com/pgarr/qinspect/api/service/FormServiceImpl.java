package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.FormDAO;
import com.pgarr.qinspect.api.dao.ItemDAO;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormDAO formDAO;

    @Autowired
    private ItemDAO itemDAO;

    @Override
    @Transactional
    public List<Form> getForms() {
        return formDAO.getForms();
    }

    @Override
    @Transactional
    public Form getForm(int id) {
        return formDAO.getForm(id);
    }

    @Override
    @Transactional
    public void saveForm(Form form) {
        formDAO.saveForm(form);
    }

    @Override
    @Transactional
    public void archiveForm(int id) {
        formDAO.archiveForm(id);
    }

    @Override
    public List<Form> getItemForms(int itemId) {

        Item item = itemDAO.getItem(itemId);

        return formDAO.getItemForms(item);
    }
}
