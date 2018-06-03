package com.pgarr.qinspect.rest.dao;

import java.util.List;

import com.pgarr.qinspect.rest.entity.Inspection;

public interface InspectionDAO {

	public List<Inspection> getInspections();

	public Inspection getInspection(int id);

	public void saveInspection(Inspection inspection);

	public void deleteInspection(int id);

}
