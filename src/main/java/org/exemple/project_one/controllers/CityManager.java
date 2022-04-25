package org.exemple.project_one.controllers;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.exemple.project_one.entities.City;

public class CityManager implements GenericDAO<City,Integer> {
    @Inject
    private EntityManager manager;
    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }
}