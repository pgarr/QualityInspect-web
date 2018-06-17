package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.InspectionDao;
import com.pgarr.qinspect.api.entity.Inspection;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InspectionServiceImpl implements InspectionService {

    @Autowired
    private InspectionDao inspectionDao;

    @Override
    @Transactional
    public List<Inspection> getAllInspections() {
        return inspectionDao.findAll();
    }

    @Override
    @Transactional
    public Inspection getInspection(long id) {
        Inspection inspection = inspectionDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

        inspection.sortResults();

        return inspection;
    }

    @Override
    @Transactional
    public void saveInspection(Inspection inspection) {
        if(inspection != null)
        inspectionDao.save(inspection);
        else throw new NullPointerException("Cannot save Inspection - it's null!");
    }

    @Override
    @Transactional
    public void deleteInspection(long id) {
        inspectionDao.deleteById(id);
    }
}
