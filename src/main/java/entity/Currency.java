package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 *
 * @author Acer
 */
@Entity
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    
  
    @Id
    private String currency;
    
    private String description;
    
    @OneToMany(mappedBy="currency")
    private List<ExchangeRate> rates;

    public Currency(String currency, String description) {
        this.currency = currency;
        this.description = description;
    }
     
    //Default constructor
    public Currency() {
    }
    
    //Getters&Setters
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
