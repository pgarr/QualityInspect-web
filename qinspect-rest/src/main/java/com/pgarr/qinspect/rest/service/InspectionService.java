package com.pgarr.qinspect.rest.service;

import java.util.List;

import com.pgarr.qinspect.rest.entity.Inspection;

public interface InspectionService {

	public List<Inspection> getInspections();

	public Inspection getInspection(int id);

	public void saveInspection(Inspection inspection);

	public void deleteInspection(int id);

}
