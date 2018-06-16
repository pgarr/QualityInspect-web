package com.pgarr.qinspect.api.service;

import java.util.List;

import com.pgarr.qinspect.api.entity.Form;

import javax.transaction.Transactional;

public interface FormService {

    List<Form> getAllForms();

    List<Form> getActiveForms();

    Form getForm(long id);

    void saveForm(Form form);

    void archiveForm(long id);

    List<Form> getActiveItemForms(long itemId);
}
