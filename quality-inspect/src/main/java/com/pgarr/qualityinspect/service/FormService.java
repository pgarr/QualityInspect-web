package com.pgarr.qualityinspect.service;

import java.util.List;

import com.pgarr.qualityinspect.entity.Form;
import com.pgarr.qualityinspect.entity.Item;

public interface FormService {

	public List<Form> getForms();

	public Form getForm(int id);

	public void saveForm(Form form);

	public List<Form> getItemForms(Item item);

}
