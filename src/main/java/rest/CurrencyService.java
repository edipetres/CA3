/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.ExchangeRate;
import facades.CurrencyFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author edipetres
 */
@Path("currency")
@RolesAllowed("User")
public class CurrencyService {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static CurrencyFacade facade = new CurrencyFacade();
    
    @GET
    @Path("dailyrates")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRates() {
        List<ExchangeRate> list = facade.getTodaysRates();
        String json = gson.toJson(list);     
        return json;

    }
}
