/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import entity.ExchangeRate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import utils.CurrencyRate;

/**
 *
 * @author edipetres
 */
public class CurrencyFacade {

    public void saveRates(ArrayList<CurrencyRate> list, String date) {
        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();
        try {
            em.getTransaction().begin();
            for (CurrencyRate cr : list) {
                Currency tempCur = getCurrency(cr.getCode());
                ExchangeRate er = new ExchangeRate(tempCur, cr.getRate(), date);
                em.persist(er);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ExchangeRate> getLatestRates() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String date = dateFormat.format(d);

        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();
        Query query = em.createQuery("SELECT e FROM ExchangeRate e WHERE e.date = :today");
        query.setParameter("today", date);
        List<ExchangeRate> exchangeRateList = query.getResultList();

        //If no rate for today, try yesterday
        if (exchangeRateList.isEmpty()) {
            System.out.println("Fetching for yesterday");
            
            Date yday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            String ydate = dateFormat.format(yday);
            query = em.createQuery("SELECT e FROM ExchangeRate e WHERE e.date = :day");
            query.setParameter("day", ydate);
            System.out.println("ydate = " + ydate);
            exchangeRateList = query.getResultList();
        }
        
        return exchangeRateList;
    }


    public List<ExchangeRate> getRatesByDay(String dateStr) {

        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();
        Query query = em.createQuery("SELECT e FROM ExchangeRate e WHERE e.date = :day");
        query.setParameter("day", dateStr);
        List<ExchangeRate> exchangeRateList = query.getResultList();
        return exchangeRateList;
    }

    public static Currency getCurrency(String currency) {
        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();
        Currency tempCurrency = null;
        try {
            tempCurrency = em.find(Currency.class, currency);
        } finally {
            em.close();
        }
        return tempCurrency;
    }
}
