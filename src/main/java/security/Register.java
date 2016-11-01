/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import facades.UserFacade;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author edipetres
 */
@Path("register")
public class Register {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String gt() {
        return "{\"txt\" : \"TEST\"}";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(String jsonString) throws JOSEException {
        String exception ="";
        try {
            JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
            String username = json.get("username").getAsString();
            String password = json.get("password").getAsString();
            JsonObject responseJson = new JsonObject();
            List<String> roles;

            UserFacade facade = new UserFacade();
            roles = facade.createUser(username, password);
            
            //If not null or empty -> successful registration
            if (roles != null && !roles.isEmpty()) {
                responseJson.addProperty("success", true);
                return Response.ok(new Gson().toJson(responseJson)).build();
            }
        } catch (Exception e) {
            System.out.println("Exception in security.Register. \n"+e.toString());
            exception = e.toString();
        }
        throw new NotAuthorizedException("Error. Username already exists.", Response.Status.UNAUTHORIZED+ "\nException: "+exception);
    }

    private List<String> authenticate(String userName, String password) {
        IUserFacade facade = UserFacadeFactory.getInstance();
        return facade.authenticateUser(userName, password);
    }
}
