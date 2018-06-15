package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Inspection;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InspectionDAOImpl implements InspectionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Inspection> getInspections() {

        Session session = sessionFactory.getCurrentSession();

        Query<Inspection> query = session.createQuery("from Inspection order by id", Inspection.class);

        return query.getResultList();
    }

    @Override
    public Inspection getInspection(int id) {

        Session session = sessionFactory.getCurrentSession();

        Inspection inspection = session.get(Inspection.class, id);

        Hibernate.initialize(inspection.getResults());

        return inspection;
    }

    @Override
    public void saveInspection(Inspection inspection) {

        Session session = sessionFactory.getCurrentSession();

        session.save(inspection);
    }

    @Override
    public void deleteInspection(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query theQuery = session.createQuery("delete from Inspection where id=:inspectionId");

        theQuery.setParameter("inspectionId", id);

        theQuery.executeUpdate();

    }
}
