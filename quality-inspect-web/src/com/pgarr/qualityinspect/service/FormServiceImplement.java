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
	private FormDAO customerDAO;

	@Override
	@Transactional
	public List<Form> getForms() {

		return customerDAO.getForms();
	}

	@Override
	@Transactional
	public Form getForm(int id) {

		return customerDAO.getForm(id);
	}

}
