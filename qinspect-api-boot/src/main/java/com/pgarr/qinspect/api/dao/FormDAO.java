package com.pgarr.qinspect.api.dao;

import java.util.List;

import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;

public interface FormDAO {

    public List<Form> getForms();

    public Form getForm(int id);

    public void saveForm(Form form);

    public void archiveForm(int id);

    public List<Form> getItemForms(Item item);
}
