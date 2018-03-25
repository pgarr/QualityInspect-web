package com.pgarr.qualityinspect.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pgarr.qualityinspect.entity.Form;
import com.pgarr.qualityinspect.entity.Item;
import com.pgarr.qualityinspect.entity.Step;
import com.pgarr.qualityinspect.service.FormService;
import com.pgarr.qualityinspect.service.ItemService;

@Controller
@RequestMapping("/form")
public class FormController {

	@Autowired
	private FormService formService;

	@Autowired
	private ItemService itemService;

	@GetMapping("/itemForms")
	public String listItemForms(@RequestParam("itemId") int itemId, Model model) {

		Item item = itemService.getItem(itemId);

		List<Form> forms = formService.getItemForms(item);

		model.addAttribute("forms", forms);
		model.addAttribute("item", item);

		return "form-list";
	}

	@GetMapping("/view")
	public String viewForm(@RequestParam("formId") int id, Model model) {

		Form form = formService.getForm(id);

		model.addAttribute("form", form);

		return "form-view";
	}

	@GetMapping("/newForm")
	public String newForm(@RequestParam("itemId") int itemId, Model model) {

		Form form = new Form();
		Item item = itemService.getItem(itemId);

		form.setItem(item);

		model.addAttribute("form", form);

		return "form-new";
	}

	@PostMapping("/addSteps")
	public String addSteps(@ModelAttribute("form") Form form, Model model) {

		System.out.println(form);

		List<Step> steps = new ArrayList<Step>();
		steps.add(new Step());

		model.addAttribute("form", form);
		model.addAttribute("steps", steps);

		return "form-new-add-steps";
	}

	@PostMapping("/saveForm")
	public String saveForm(@ModelAttribute("form") Form form) {

		formService.saveForm(form);

		return "redirect:/form/list";
	}

}
