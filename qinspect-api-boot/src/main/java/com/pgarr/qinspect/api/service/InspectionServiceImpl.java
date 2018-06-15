package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.InspectionDAO;
import com.pgarr.qinspect.api.entity.Inspection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class InspectionServiceImpl implements InspectionService {

    @Autowired
    private InspectionDAO inspectionDAO;

    @Override
    @Transactional
    public List<Inspection> getInspections() {
        return inspectionDAO.getInspections();
    }

    @Override
    @Transactional
    public Inspection getInspection(int id) {
        return inspectionDAO.getInspection(id);
    }

    @Override
    @Transactional
    public void saveInspection(Inspection inspection) {
        inspectionDAO.saveInspection(inspection);
    }

    @Override
    @Transactional
    public void deleteInspection(int id) {
        inspectionDAO.deleteInspection(id);
    }
}
