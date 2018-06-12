package com.pgarr.qinspect.api.service;

import java.util.List;

import com.pgarr.qinspect.api.entity.Inspection;

public interface InspectionService {

	public List<Inspection> getInspections();

	public Inspection getInspection(int id);

	public void saveInspection(Inspection inspection);

	public void deleteInspection(int id);

}
