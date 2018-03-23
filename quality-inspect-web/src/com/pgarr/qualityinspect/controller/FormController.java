package com.pgarr.qualityinspect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/view")
	public String viewForm(@RequestParam("formId") int id, Model model) {

		Form form = formService.getForm(id);

		model.addAttribute("form", form);

		return "form-view";
	}

}
