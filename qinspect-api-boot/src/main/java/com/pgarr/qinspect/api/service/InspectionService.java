package com.pgarr.qinspect.api.service;

import java.util.List;

import com.pgarr.qinspect.api.entity.Inspection;

public interface InspectionService {

	List<Inspection> getAllInspections();

	Inspection getInspection(long id);

	void saveInspection(Inspection inspection);

	void deleteInspection(long id);

}
