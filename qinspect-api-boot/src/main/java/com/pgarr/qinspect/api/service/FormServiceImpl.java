package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.FormDao;
import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormDao formDAO;

    @Override
    @Transactional
    public List<Form> getAllForms() {

        Iterable<Form> iterable = formDAO.findAll();

        List<Form> forms = new ArrayList<>();
        iterable.forEach(forms::add);

        return forms;
    }

    @Override
    @Transactional
    public List<Form> getActiveForms() {
        return formDAO.findByArchivedFalse();
    }

    @Override
    @Transactional
    @EntityGraph(attributePaths = {"steps"})
    public Form getForm(long id) {
        return formDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

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
    @Transactional
    public List<Form> getActiveItemForms(long itemId) {

        return formDAO.findByArchivedFalseAndItem_Id(itemId);

    }
}
