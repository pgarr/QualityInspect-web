package com.pgarr.qualityinspect.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pgarr.qualityinspect.entity.Form;
import com.pgarr.qualityinspect.entity.Item;

@Repository
public class FormDAOImplement implements FormDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Form getForm(int id) {

		Session session = sessionFactory.getCurrentSession();

		Form form = session.get(Form.class, id);

		System.out.println(form.getSteps());

		return form;
	}

	@Override
	public void saveForm(Form form) {

		Session session = sessionFactory.getCurrentSession();

		session.save(form);

	}

	@Override
	public List<Form> getForms() {

		Session session = sessionFactory.getCurrentSession();

		Query<Form> query = session.createQuery("from Form order by name", Form.class);

		List<Form> forms = query.getResultList();

		return forms;
	}

	@Override
	public List<Form> getItemForms(Item item) {

		Session session = sessionFactory.getCurrentSession();

		Query<Form> query = session.createQuery("from Form where item=:item order by name", Form.class);

		query.setParameter("item", item);

		List<Form> forms = query.getResultList();

		return forms;
	}

}
