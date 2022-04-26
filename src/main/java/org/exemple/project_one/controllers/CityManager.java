package org.exemple.project_one.controllers;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.exemple.project_one.entities.City;
@Stateless
@LocalBean
public class CityManager implements GenericDAO<City,Integer> {
    @Inject
    private EntityManager manager;
    @Override
    public EntityManager getEntityManager() {
        return manager;
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }
}