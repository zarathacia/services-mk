package org.exemple.project_one.controllers;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.exemple.project_one.entities.RootEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface GenericDAO <E extends RootEntity<ID>,ID extends java.io.Serializable> {
    EntityManager getEntityManager();
    Class<E> getEntityClass();
    default boolean existsById(ID id){
        if(id==null){
            return false;
        }
        final CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        final Root<E> root= cq.from(getEntityClass());
        cq.select(getEntityManager().getCriteriaBuilder().count(root));
        cq.where(getEntityManager().getCriteriaBuilder().equal(root.get(idColumnName()),id));
        final TypedQuery<Long> query= getEntityManager().createQuery(cq);
        return query.getSingleResult()>0;
    }

    default String idColumnName(){
        if(getEntityClass().isAnnotationPresent(AttributeOverrides.class)){
            for(AttributeOverride ao:getEntityClass().getAnnotation(AttributeOverrides.class).value()){
                if(ao.name().equals("id")){
                    return ao.column().name();
                }
            }
        }
        if(getEntityClass().isAnnotationPresent(AttributeOverride.class)){
            final AttributeOverride ao = getEntityClass().getAnnotation(AttributeOverride.class);
            if(ao.name().equals("id")){
                return ao.column().name();
            }
        }
        return "id";
    }

    @Transactional
    default <S extends E> S save(S entity){
        if(!existsById(entity.getId())){//insertion: add a new entity
            getEntityManager().persist(entity);
            return entity;
        }
        //Update: Modify an existing entity
        return getEntityManager().merge(entity);
    }

    @Transactional
    default <S extends E> Iterable<S> saveAll(Iterable<S> entities){
        entities.forEach(this::save);
        return entities;
    }

    @Transactional
    default E edit(ID id, Consumer<E> updateFewAttribute){
        E entity = getEntityManager().find(getEntityClass(),id);
        if(entity == null){
            throw new IllegalArgumentException("Unknown entity with id: "+ id );
        }
        updateFewAttribute.accept(entity);
        getEntityManager().flush();
        return entity;
    }

    @Transactional
    default void delete(E entity){
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Transactional
    default void deleteAll(Iterable<? extends E> entities){
        entities.forEach(this::delete);
    }

    default Optional<E> findById(ID id){
        return Optional.ofNullable(getEntityManager().find(getEntityClass(),id));
    }

    default List<E> findAll(){
        final CriteriaQuery<E> cq = getEntityManager().getCriteriaBuilder().createQuery(getEntityClass());
        cq.select(cq.from(getEntityClass()));
        return getEntityManager().createQuery(cq).getResultList();
    }

    default List<E> findRange(int first, int last){
        final CriteriaQuery<E> cq = getEntityManager().getCriteriaBuilder().createQuery(getEntityClass());
        cq.select(cq.from(getEntityClass()));
        TypedQuery<E> query = getEntityManager().createQuery(cq);
        query.setMaxResults(last-first+1);
        query.setFirstResult(first);
        return query.getResultList();
    }

    default long count(){
        final CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        Root<E> root = cq.from(getEntityClass());
        cq.select(getEntityManager().getCriteriaBuilder().count(root));
        TypedQuery<Long> query = getEntityManager().createQuery(cq);
        return query.getSingleResult();
    }

}

