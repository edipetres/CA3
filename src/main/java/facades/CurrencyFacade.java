/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import entity.ExchangeRate;
import java.awt.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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
