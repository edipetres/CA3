package entity;

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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import rest.CurrencyService;
import utils.CurrencyRate;
import utils.ScheduledTaskManager;

/**
 *
 * @author Acer
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //fetchData();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();
        CurrencyFacade cfacade = new CurrencyFacade();
        double currencyRate = cfacade.getCurrencyRate("aud");
        System.out.println("currencyRate = " + currencyRate);
        
//        List<ExchangeRate> list = cfacade.getLatestRates();
//        System.out.println("list size: "+list.size());
//        //Convert the object to be json compatible
//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("date", list.get(0).getDate());
//
//            JSONArray jarray = new JSONArray();
//            for (ExchangeRate rate : list) {
//                JSONObject tempObj = new JSONObject();
//                tempObj.put("currency", rate.getCurrency().getCurrency());
//                tempObj.put("rate", rate.getRate());
//                
//                jarray.put(tempObj);
//            }
//            obj.put("currencies", jarray);
//
//
//        } catch (JSONException ex) {
//            Logger.getLogger(CurrencyService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        String json = gson.toJson(obj);
//        System.out.println("json = " + json);
////List<ExchangeRate> ratesByDay = cfacade.getRatesByDay("2011-01-18");
//        List<ExchangeRate> ratesByDay = cfacade.getLatestRates();
//        System.out.println("result "+ratesByDay.size());
//        for (ExchangeRate rate : ratesByDay) {
//            System.out.println("Rate: " + rate.getDate() + " " + rate.getId());
//        }
//        
        
        
       
    }

    private static void fetchData() {
        //Code for fetching today's rates
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
            //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            currencyFacade.saveRates(currencies, date);
            System.out.println("sould have the rates saved.");
        } catch (IOException | JSONException ex) {
            Logger.getLogger(ScheduledTaskManager.class.getName()).log(Level.SEVERE, null, ex);
        }
//
//
//        em.getTransaction().begin();
//        em.persist(new Currency("AUD", "Australian dollars"));
//        em.persist(new Currency("BGN", "Bulgarian lev"));
//        em.persist(new Currency("BRL", "Brazilian real"));
//        em.persist(new Currency("CAD", "Canadian dollars"));
//        em.persist(new Currency("CHF", "Swiss francs"));
//        em.persist(new Currency("CNY", "Chinese yuan renminbi"));
//        em.persist(new Currency("CZK", "Czech koruny"));
//        em.persist(new Currency("EUR", "Euro"));
//        em.persist(new Currency("GBP", "Pounds sterling"));
//        em.persist(new Currency("HKD", "Pounds sterling"));
//        em.persist(new Currency("HRK", "Croatian kuna"));
//        em.persist(new Currency("HUF", "Hungarian forints"));
//        em.persist(new Currency("IDR", "Indonesian rupiah"));
//        em.persist(new Currency("ILS", "Israeli shekel"));
//        em.persist(new Currency("INR", "Indian rupee"));
//        em.persist(new Currency("ISK", "Icelandic kronur"));
//        em.persist(new Currency("JPY", "Japanese yen"));
//        em.persist(new Currency("KRW", "South Korean won"));
//        em.persist(new Currency("MXN", "Mexican peso"));
//        em.persist(new Currency("MYR", "Malaysian ringgit"));
//        em.persist(new Currency("NOK", "Norwegian kroner"));
//        em.persist(new Currency("NZD", "New Zealand dollars"));
//        em.persist(new Currency("PHP", "Philippine peso"));
//        em.persist(new Currency("PLN", "Polish zlotys"));
//        em.persist(new Currency("RON", "Romanian leu"));
//        em.persist(new Currency("RUB", "Russian rouble"));
//        em.persist(new Currency("SEK", "Swedish kronor"));
//        em.persist(new Currency("SGD", "Singapore dollars"));
//        em.persist(new Currency("THB", "Thai baht"));
//        em.persist(new Currency("TRY", "Turkish lira"));
//        em.persist(new Currency("USD", "US dollars"));
//        em.persist(new Currency("XDR", "SDR (Calculated **)"));
//        em.persist(new Currency("ZAR", "South African rand"));
//        em.getTransaction().commit();
//        
    }

}
