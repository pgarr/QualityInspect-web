package com.pgarr.qualityinspect.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pgarr.qualityinspect.entity.Item;

@Repository
public class ItemDAOImplement implements ItemDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Item> getItems() {

		Session session = sessionFactory.getCurrentSession();

		Query<Item> query = session.createQuery("from Item order by name", Item.class);

		List<Item> items = query.getResultList();

		return items;
	}

}
