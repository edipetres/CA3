package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import facades.UserFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("admin")
@RolesAllowed("Admin")
public class Admin {
  
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static UserFacade facade = new UserFacade();
    
  @GET
  @Path("users")
  @Produces(MediaType.APPLICATION_JSON)
  public String getUsers(){
     List<User> userList = facade.getAllUsers();
     
     
     String json = gson.toJson(userList);
     return json;
   }
 
}
