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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        List<ExchangeRate> list = facade.getLatestRates();
        System.out.println("list size: "+list.size());
        //Convert the object to be json compatible
        JSONObject obj = new JSONObject();
        try {
            obj.put("date", list.get(0).getDate());

            JSONArray jarray = new JSONArray();
            for (ExchangeRate rate : list) {
                JSONObject tempObj = new JSONObject();
                tempObj.put("currency", rate.getCurrency().getCurrency());
                tempObj.put("rate", rate.getRate());
                
                jarray.put(tempObj);
            }
            obj.put("currencies", jarray);
            

        } catch (JSONException ex) {
            Logger.getLogger(CurrencyService.class.getName()).log(Level.SEVERE, null, ex);
        }

        String json = gson.toJson(obj);
        return json;

    }

    @GET
    @Path("calculator/{amount}/{fromcurrency}/{tocurrency}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething(@PathParam("amount") int amount,
            @PathParam("fromcurrency") String fromCurrency,
            @PathParam("tocurrency") String toCurrency) {
        
        
        
        
        
        return "{'response': '756.14'}";
    }
}
