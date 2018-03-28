package com.pgarr.qualityinspect.controller;

import java.util.List;
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

		Item item = itemService.getItem(itemId);

		Form form = new Form();

		form.setItem(item);

		Step step = new Step();
		step.setNumber(1);
		form.addStep(step);

		System.out.println(form);

		model.addAttribute("form", form);

		return "form-new";
	}

	@PostMapping("/addStep")
	public String addSteps(@ModelAttribute("form") Form form, Model model) {

		Step step = new Step();
		form.addStep(step);

		model.addAttribute("form", form);

		return "form-new";
	}

	@PostMapping("/saveForm")
	public String saveForm(@ModelAttribute("form") Form form) {

		formService.saveForm(form);

		return "redirect:/item/list";
	}

}
