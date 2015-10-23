package com.sportsschedule.dao;

import org.hibernate.*;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenericDAOImpl implements GenericDAO {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public <T> T save(final T o){
        return (T) sessionFactory.getCurrentSession().save(o);
    }

    @Override
    public void delete(final Object object){
        sessionFactory.getCurrentSession().delete(object);
    }

    /***/
    @Override
    public <T> T get(final Class<T> type, final Long id){
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    /***/
    @Override
    public <T> T merge(final T o)   {
        return (T) sessionFactory.getCurrentSession().merge(o);
    }

    /***/
    @Override
    public <T> void saveOrUpdate(final T o){
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    @Override
    public <T> List<T> getAll(Class<T> type) {
        final Session session = sessionFactory.getCurrentSession();
        final Criteria crit = session.createCriteria(type);
        return crit.list();
    }

    @Override
    public <T> T findById(Class<T> type, int id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    @Override
    public <T> List<T> findByQuery(String query, HashMap<String, Object> params, Class<T> type) {
        Query query1 = sessionFactory.getCurrentSession().createQuery(query);

        for(Map.Entry<String, Object> entry : params.entrySet()){
            query1.setParameter(entry.getKey(), entry.getValue());
        }

        return (List<T>) query1.list();
    }

    @Override
    public <T> T findSingleByQuery(String query, HashMap<String, Object> param, Class<T> type){
        List<T> queryList = findByQuery(query, param, type);

        if(queryList == null || queryList.size() == 0){
            return null;
        }

        return queryList.get(0);
    }

}
