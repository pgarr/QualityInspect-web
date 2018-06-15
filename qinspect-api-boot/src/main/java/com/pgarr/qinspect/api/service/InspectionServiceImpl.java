package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.InspectionDao;
import com.pgarr.qinspect.api.entity.Inspection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class InspectionServiceImpl implements InspectionService {

    @Autowired
    private InspectionDao inspectionDAO;

    @Override
    @Transactional
    public List<Inspection> getInspections() {
        return inspectionDAO.findAll();
    }

    @Override
    @Transactional
    public Inspection getInspection(long id) {
        // requires lazy init
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
