package com.pgarr.qinspect.api.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgarr.qinspect.api.entity.Item;
import com.pgarr.qinspect.api.service.ItemService;

@RestController
@RequestMapping("/api-it")
public class ItemRestController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/items")
	public List<Item> getItems() {

		return itemService.getItems();
	}

	@GetMapping("/items/{id}")
	public Item getItem(@PathVariable int id) {

		return itemService.getItem(id);
	}

	@GetMapping("/items/{id}/forms")
	public Item getItemWithForms(@PathVariable int id) {

		return itemService.getItemWithForms(id);
	}

	@PostMapping
	public void saveItem(@RequestBody Item item) {

		itemService.saveItem(item);

	}

}
