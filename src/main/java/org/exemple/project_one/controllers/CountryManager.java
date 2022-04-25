package org.exemple.project_one.controllers;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.exemple.project_one.entities.Country;
import org.exemple.project_one.entities.SimplePKEntity;
import org.exemple.project_one.entities.User;

public class CountryManager implements GenericDAO<Country,Integer> {
        @Inject
        private EntityManager manager;
        @Override
        public EntityManager getEntityManager() {
            return null;
        }

        @Override
        public Class<Country> getEntityClass() {
            return Country.class;
        }
    }


