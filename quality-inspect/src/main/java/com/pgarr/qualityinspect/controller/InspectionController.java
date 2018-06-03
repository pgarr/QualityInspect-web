package com.pgarr.qualityinspect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pgarr.qualityinspect.entity.Inspection;
import com.pgarr.qualityinspect.service.InspectionService;

@Controller
@RequestMapping("/inspection")
public class InspectionController {

	@Autowired
	private InspectionService inspectionService;

	@GetMapping("/list")
	public String listInspections(Model model) {

		List<Inspection> inspections = inspectionService.getInspections();

		model.addAttribute("inspections", inspections);

		return "inspection-list";
	}

	@GetMapping("/view")
	public String viewInspection(@RequestParam("inspectionId") int inspectionId, Model model) {

		Inspection inspection = inspectionService.getInspection(inspectionId);

		System.out.println(inspection);

		model.addAttribute("inspection", inspection);

		return "inspection-view";
	}

	@GetMapping("/newInspection")
	public String newInspection(Model model) {

		return null;
	}

}
