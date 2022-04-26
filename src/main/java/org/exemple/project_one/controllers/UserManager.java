package org.exemple.project_one.controllers;

import de.mkammerer.argon2.Argon2;
import jakarta.ejb.EJBException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.exemple.project_one.entities.User;
import org.exemple.project_one.util.Argon2Utility;

import java.util.HashSet;
import java.util.Set;

@Stateless
@LocalBean
public class UserManager implements GenericDAO<User,Short> {
    @Inject
    private EntityManager manager;
    @Override
    public EntityManager getEntityManager() {
        return manager;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    public User findByUsername(String username){
        TypedQuery<User> query = manager.createQuery(
                "SELECT u FROM User u WHERE u.username=:username",
                User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public User authenticate(String username, String password){
        User user = findByUsername(username);
        if(user != null && Argon2Utility.check(user.getPassword(),password.toCharArray())){
            return user;
        }
        throw new EJBException("Failed to authenticate user with username: "+ username);
    }

    public boolean hasRole(User user, Role role){
        return (user.getRoles()&role.getValue())!=0L;
    }

    public void addRole(User user, Role role){
        user.setRoles(user.getRoles()|role.getValue());
    }

    public void removeRole(User user, Role role){
        user.setRoles(user.getRoles()&(~role.getValue()));
    }

    public Role[] getRoles(User user){
        Set<Role> roles = new HashSet<>();
        long userRoles=user.getRoles();
        for(Role role:Role.values()){
            if((userRoles&role.getValue())!=0L){
                roles.add(role);
            }
        }
        return roles.toArray(new Role[0]);
    }
}
