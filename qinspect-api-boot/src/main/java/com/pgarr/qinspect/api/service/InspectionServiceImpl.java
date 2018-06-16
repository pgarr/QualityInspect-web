package com.pgarr.qinspect.api.service;

import com.pgarr.qinspect.api.dao.InspectionDao;
import com.pgarr.qinspect.api.entity.Inspection;
import com.pgarr.qinspect.api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InspectionServiceImpl implements InspectionService {

    @Autowired
    private InspectionDao inspectionDao;

    @Override
    @Transactional
    public List<Inspection> getInspections() {
        return inspectionDao.findAll();
    }

    @Override
    @Transactional
    @EntityGraph(attributePaths = {"results"})
    public Inspection getInspection(long id) {
        return inspectionDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));

    }

    @Override
    @Transactional
    public void saveInspection(Inspection inspection) {
        inspectionDao.save(inspection);
    }

    @Override
    @Transactional
    public void deleteInspection(long id) {
        inspectionDao.deleteById(id);
    }
}
