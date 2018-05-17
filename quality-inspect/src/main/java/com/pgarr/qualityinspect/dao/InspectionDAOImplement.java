package com.pgarr.qualityinspect.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pgarr.qualityinspect.entity.Inspection;

@Repository
public class InspectionDAOImplement implements InspectionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Inspection> getInspections() {

		Session session = sessionFactory.getCurrentSession();

		Query<Inspection> query = session.createQuery("from Inspection order by id", Inspection.class);

		List<Inspection> inspections = query.getResultList();

		return inspections;
	}

	@Override
	public Inspection getInspection(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInspection(Inspection inspection) {
		// TODO Auto-generated method stub

	}

}
