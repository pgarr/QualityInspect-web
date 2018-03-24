package com.pgarr.qualityinspect.dao;

import java.util.List;

import com.pgarr.qualityinspect.entity.Form;

public interface FormDAO {

	public List<Form> getForms();

	public Form getForm(int id);

	public void saveForm(Form form);

}
