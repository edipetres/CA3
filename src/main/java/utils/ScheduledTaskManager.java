/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CurrencyFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;



/**
 *
 * @author edipetres
 */
@Singleton
public class ScheduledTaskManager {
    @Schedule(hour="*", minute="*", second="*/15", persistent=false)
    public void dailyJob() {
        try {
            // Do your job here which should run every start of day.
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
            URLConnection con = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en").openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            String data = "";
            while ((inputLine = in.readLine()) != null){
                data += inputLine;
            }
            JSONObject json = XML.toJSONObject(data);
            
            JSONObject exchangeRates = json.getJSONObject("exchangerates");
            JSONObject dailyRates = exchangeRates.getJSONObject("dailyrates");
            String date = dailyRates.get("id").toString();
            
            ArrayList<CurrencyRate> currencies = new ArrayList();
            JSONArray jsonCurrencyArray = dailyRates.getJSONArray("currency");
            for (int i = 0; i < jsonCurrencyArray.length(); i++) {
                JSONObject singleCurrency = (JSONObject) jsonCurrencyArray.get(i);
                String code = singleCurrency.get("code").toString();
                String rate = singleCurrency.get("rate").toString();
                currencies.add(new CurrencyRate(code,rate));
            }
            
            //Call method in facade to save data including the date and list of currencies
            //DBFacade facade = new DBFacade();
            //facade.saveRates(date, currencies);
            CurrencyFacade currencyFacade = new CurrencyFacade();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            currencyFacade.saveRates(currencies, dateFormat.toString());
            System.out.println("sould have the rates saved.");
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ScheduledTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        new ScheduledTaskManager().dailyJob();
    }
    
}
