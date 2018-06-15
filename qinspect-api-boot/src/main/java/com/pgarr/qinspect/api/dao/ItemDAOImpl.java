package com.pgarr.qinspect.api.dao;

import com.pgarr.qinspect.api.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Item> getItems() {

        Session session = sessionFactory.getCurrentSession();

        Query<Item> query = session.createQuery("from Item order by name", Item.class);

        return query.getResultList();
    }

    @Override
    public Item getItem(int id) {

        Session session = sessionFactory.getCurrentSession();

        return session.get(Item.class, id);
    }

    @Override
    public void saveItem(Item item) {

        Session session = sessionFactory.getCurrentSession();

        session.save(item);
    }
}
