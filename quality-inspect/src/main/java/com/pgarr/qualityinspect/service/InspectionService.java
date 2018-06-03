package com.pgarr.qualityinspect.service;

import java.util.List;

import com.pgarr.qualityinspect.entity.Inspection;

public interface InspectionService {

	public List<Inspection> getInspections();

	public Inspection getInspection(int inspectionId);

	public void saveInspection(Inspection inspection);

}
