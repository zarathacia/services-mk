package org.exemple.project_one.controllers;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.exemple.project_one.entities.User;

public class UserManager implements GenericDAO<User,Short> {
    @Inject
    private EntityManager manager;
    @Override
    public EntityManager getEntityManager() {
        return null;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
