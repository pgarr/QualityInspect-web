package com.pgarr.qinspect.api.service;

import java.util.List;

import com.pgarr.qinspect.api.entity.Form;

public interface FormService {

	public List<Form> getForms();

	public Form getForm(int id);

	public void saveForm(Form form);

	public void archiveForm(int id);

}
