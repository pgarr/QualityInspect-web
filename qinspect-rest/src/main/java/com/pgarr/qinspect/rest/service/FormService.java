package com.pgarr.qinspect.rest.service;

import java.util.List;

import com.pgarr.qinspect.rest.entity.Form;
import com.pgarr.qinspect.rest.entity.Item;

public interface FormService {

	public List<Form> getForms();

	public Form getForm(int id);

	public void saveForm(Form form);

	public void archiveForm(int id);

}
