package com.sportsschedule.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GenericDAO {

    <T> T save(final T o);

    void delete(final Object object);

    <T> T get(final Class<T> type, final Long id);

    <T> T merge(final T o);

    <T> void saveOrUpdate(final T o);

    <T> List<T> getAll(final Class<T> type);

    <T> T findById(final Class<T> type, int id);

    <T> List<T> findByQuery(String query, HashMap<String, Object> param, Class<T> type);

    <T> T findSingleByQuery(String query, HashMap<String, Object> param, Class<T> type);

}
