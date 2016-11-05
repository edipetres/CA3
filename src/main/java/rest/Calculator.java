/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author edipetres
 */
@Path("currency")
@RolesAllowed("User")
public class Calculator {
    
    @GET
    @Path("calculator/{amount}/{fromcurrency}/{tocurrency}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething (@PathParam("amount") int amount,
                                @PathParam("fromcurrency") String fromCurrency,
                                @PathParam("tocurrency") String toCurrency)
    {
        return "{'amount': '756.14'}";
    }
}