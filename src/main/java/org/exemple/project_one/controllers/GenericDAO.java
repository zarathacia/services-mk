package org.exemple.project_one.controllers;

import jakarta.persistence.EntityManager;
import org.exemple.project_one.entities.RootEntity;

public interface GenericDAO <E extends RootEntity<ID>,ID extends java.io.Serializable> {
    EntityManager getEntityManager();
    Class<E> getEntityClass();
}
