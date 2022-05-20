package org.exemple.project_one.boundaries;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.exemple.project_one.controllers.UserManager;
import org.exemple.project_one.entities.User;

import java.util.List;
import java.util.logging.Logger;

@Path("/users")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEndpoint {
    @Inject
    private Logger logger;
    @EJB
    private UserManager userManager;
    @GET
    public Response fetchAllUsers(){
        GenericEntity<List<User>> entities = new GenericEntity<>(userManager.findAll()){};
        return Response.ok().entity(entities).build();
    }


}
