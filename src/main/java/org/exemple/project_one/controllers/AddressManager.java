package org.exemple.project_one.controllers;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.exemple.project_one.entities.Address;

public class AddressManager implements GenericDAO<Address,Integer> {
    @Inject
    private EntityManager manager;
    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }
}
