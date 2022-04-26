package org.exemple.project_one.controllers;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.exemple.project_one.entities.Country;

@Stateless
@LocalBean
public class CountryManager implements GenericDAO<Country,Integer> {
        @Inject
        private EntityManager manager;
        @Override
        public EntityManager getEntityManager() {
            return manager;
        }

        @Override
        public Class<Country> getEntityClass() {
            return Country.class;
        }
    }


