package com.pgarr.qinspect.rest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgarr.qinspect.rest.entity.Form;
import com.pgarr.qinspect.rest.service.FormService;

@RestController
@RequestMapping("/api-fo")
public class FormRestController {

	@Autowired
	private FormService formService;

	@GetMapping("/forms")
	public List<Form> getForms() {

		return formService.getForms();
	}

	@GetMapping("/forms/{id}")
	public Form getForm(@PathVariable int id) {

		return formService.getForm(id);

	}

	@PostMapping("/forms")
	public void saveForm(@RequestBody Form form) {

		formService.saveForm(form);

	}

	@DeleteMapping("/forms/{id}")
	public void archiveForm(@PathVariable int id) {

		formService.archiveForm(id);
	}

}
