package com.pgarr.qualityinspect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pgarr.qualityinspect.dao.InspectionDAO;
import com.pgarr.qualityinspect.entity.Inspection;

@Service
public class InspectionServiceImplement implements InspectionService {

	@Autowired
	private InspectionDAO inspectionDAO;

	@Override
	@Transactional
	public List<Inspection> getInspections() {

		return inspectionDAO.getInspections();
	}

	@Override
	@Transactional
	public Inspection getInspection(int inspectionId) {

		return inspectionDAO.getInspection(inspectionId);
	}

	@Override
	@Transactional
	public void saveInspection(Inspection inspection) {
		// TODO Auto-generated method stub

	}

}
