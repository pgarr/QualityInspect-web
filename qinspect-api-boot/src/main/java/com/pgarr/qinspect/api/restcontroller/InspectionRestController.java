package com.pgarr.qinspect.api.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgarr.qinspect.api.entity.Inspection;
import com.pgarr.qinspect.api.service.InspectionService;

@RestController
@RequestMapping("/apiin")
public class InspectionRestController {

    @Autowired
    private InspectionService inspectionService;

    @GetMapping("/inspections")
    public List<Inspection> getInspections() {
        return inspectionService.getAllInspections();
    }

    @GetMapping("/inspections/{id}")
    public Inspection getInspection(@PathVariable int id) {
        return inspectionService.getInspection(id);
    }

    @PostMapping("/inspections")
    public void saveInspection(@RequestBody Inspection inspection) {
        inspectionService.saveInspection(inspection);
    }

    @PutMapping("/inspections")
    public void updateInspection(@RequestBody Inspection inspection) {
        inspectionService.saveInspection(inspection);
    }

    @DeleteMapping("/inspections/{id}")
    public void deleteInspection(@PathVariable int id) {
        inspectionService.deleteInspection(id);
    }

}
