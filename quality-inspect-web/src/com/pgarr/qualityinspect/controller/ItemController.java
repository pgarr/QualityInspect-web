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

import com.pgarr.qualityinspect.entity.Item;
import com.pgarr.qualityinspect.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/list")
	public String listItems(Model model) {

		List<Item> items = itemService.getItems();

		model.addAttribute("items", items);

		return "item-list";
	}

	@GetMapping("/viewForms")
	public String viewItem(@RequestParam("itemId") int id, Model model) {

		Item item = itemService.getItem(id);

		model.addAttribute("item", item);

		return "redirect:/form/list";
	}

	@GetMapping("/newItem")
	public String addItem(Model model) {

		Item item = new Item();

		model.addAttribute("item", item);

		return "item-new";
	}

	@PostMapping("/saveItem")
	public String saveItem(@ModelAttribute("item") Item item) {

		itemService.saveItem(item);

		return "redirect:/item/list";
	}

}
