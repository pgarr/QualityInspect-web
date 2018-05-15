package com.pgarr.qualityinspect.dao;

import java.util.List;

import com.pgarr.qualityinspect.entity.Form;
import com.pgarr.qualityinspect.entity.Item;

public interface FormDAO {

	public List<Form> getForms();

	public Form getForm(int id);

	public void saveForm(Form form);

	public List<Form> getItemForms(Item item);

}
