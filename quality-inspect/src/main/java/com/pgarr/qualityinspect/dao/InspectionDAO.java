package com.pgarr.qualityinspect.dao;

import java.util.List;

import com.pgarr.qualityinspect.entity.Inspection;

public interface InspectionDAO {

	public List<Inspection> getInspections();

	public Inspection getInspection(int id);

	public void saveInspection(Inspection inspection);

}
