package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.InspectionDao;
import com.pgarr.qinspect.api.entity.Inspection;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class InspectionServiceImpl implements InspectionService {

    @Autowired
    private InspectionDao inspectionDAO;

    @Override
    @Transactional
    public List<Inspection> getInspections() {

        Iterable<Inspection> iterable = inspectionDAO.findAll();

        List<Inspection> inspections = new ArrayList<>();
        iterable.forEach(inspections::add);

        return inspections;
    }

    @Override
    @Transactional
    @EntityGraph(attributePaths = {"results"})
    public Inspection getInspection(long id) {
        return inspectionDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

    }

    @Override
    @Transactional
    public void saveInspection(Inspection inspection) {
        inspectionDAO.save(inspection);
    }

    @Override
    @Transactional
    public void deleteInspection(long id) {
        inspectionDAO.deleteById(id);
    }
}
