package com.pgarr.qualityinspect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pgarr.qualityinspect.entity.Form;
import com.pgarr.qualityinspect.service.FormService;

@Controller
@RequestMapping("/form")
public class FormController {

	@Autowired
	private FormService formService;

	@GetMapping("/list")
	public String listForms(Model model) {

		List<Form> forms = formService.getForms();

		model.addAttribute("forms", forms);

		return "list-forms";
	}

}
