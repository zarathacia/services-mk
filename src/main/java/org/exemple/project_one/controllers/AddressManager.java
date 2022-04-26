package org.exemple.project_one.controllers;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.exemple.project_one.entities.Address;
@Stateless
@LocalBean
public class AddressManager implements GenericDAO<Address,Integer> {
    @Inject
    private EntityManager manager;
    @Override
    public EntityManager getEntityManager() {
        return manager;
    }

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }
}
