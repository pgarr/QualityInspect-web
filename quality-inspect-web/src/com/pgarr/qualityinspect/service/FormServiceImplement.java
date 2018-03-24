package com.pgarr.qualityinspect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pgarr.qualityinspect.dao.FormDAO;
import com.pgarr.qualityinspect.entity.Form;

@Service
public class FormServiceImplement implements FormService {

	@Autowired
	private FormDAO formDAO;

	@Override
	@Transactional
	public List<Form> getForms() {

		return formDAO.getForms();
	}

	@Override
	@Transactional
	public Form getForm(int id) {

		return formDAO.getForm(id);
	}

	@Override
	@Transactional
	public void saveForm(Form form) {

		formDAO.saveForm(form);
	}

}
