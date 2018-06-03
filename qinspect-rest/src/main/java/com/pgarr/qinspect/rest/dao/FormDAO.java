package com.pgarr.qinspect.rest.dao;

import java.util.List;

import com.pgarr.qinspect.rest.entity.Form;
import com.pgarr.qinspect.rest.entity.Item;

public interface FormDAO {

	public List<Form> getForms();

	public Form getForm(int id);

	public void saveForm(Form form);

}
