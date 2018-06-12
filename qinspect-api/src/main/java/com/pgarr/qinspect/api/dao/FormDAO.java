package com.pgarr.qinspect.api.dao;

import java.util.List;

import com.pgarr.qinspect.api.entity.Form;

public interface FormDAO {

	public List<Form> getForms();

	public Form getForm(int id);

	public void saveForm(Form form);

}
