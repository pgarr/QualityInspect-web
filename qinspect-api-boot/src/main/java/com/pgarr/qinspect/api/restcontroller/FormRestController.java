package com.pgarr.qinspect.api.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.service.FormService;

@RestController
@RequestMapping("/apifo")
public class FormRestController {

    @Autowired
    private FormService formService;

    @GetMapping("/forms")
    public List<Form> getForms() {
        return formService.getAllForms();
    }

    @GetMapping("/forms/{id}")
    public Form getForm(@PathVariable int id) {
        return formService.getForm(id);
    }

    @GetMapping("/forms/item/{itemId}")
    public List<Form> getItemForms(@PathVariable int itemId) {
        return formService.getActiveItemForms(itemId);
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
