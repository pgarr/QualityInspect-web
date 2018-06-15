package com.pgarr.qinspect.api.dao;


import com.pgarr.qinspect.api.entity.Form;
import com.pgarr.qinspect.api.entity.Item;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormDAOimpl implements FormDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Form> getForms() {

        Session session = sessionFactory.getCurrentSession();

        Query<Form> query = session.createQuery("from Form order by name", Form.class);

        return query.getResultList();
    }

    @Override
    public Form getForm(int id) {

        Session session = sessionFactory.getCurrentSession();

        Form form = session.get(Form.class, id);

        Hibernate.initialize(form.getSteps());

        return form;
    }

    @Override
    public void saveForm(Form form) {

        Session session = sessionFactory.getCurrentSession();

        session.save(form);
    }

    //   Method sets field archived = true
    //   Without need for updating whole record
    @Override
    public void archiveForm(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("update Form set archived=:archived" + "where id=:formId");

        query.setParameter("archived", true);
        query.setParameter("formId", id);

        int result = query.executeUpdate();
    }

    @Override
    public List<Form> getItemForms(Item item) {
        Session session = sessionFactory.getCurrentSession();

        Query<Form> query = session.createQuery("from Form where item=:item order by name", Form.class);

        query.setParameter("item", item);

        return query.getResultList();
    }
}
