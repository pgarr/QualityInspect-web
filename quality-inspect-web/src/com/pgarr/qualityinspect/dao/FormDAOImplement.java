package com.pgarr.qualityinspect.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pgarr.qualityinspect.entity.Form;

@Repository
public class FormDAOImplement implements FormDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Form> getForms() {

		Session session = sessionFactory.getCurrentSession();

		Query<Form> query = session.createQuery("from Form", Form.class);

		List<Form> forms = query.getResultList();

		return forms;
	}

	@Override
	public Form getForm(int id) {

		Session session = sessionFactory.getCurrentSession();

		Form form = session.get(Form.class, id);

		System.out.println(form.getObject().getObjectDetail());

		System.out.println(form.getSteps());

		return form;
	}

}
