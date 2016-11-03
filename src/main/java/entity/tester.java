package entity;

import javax.persistence.Persistence;

/**
 *
 * @author Acer
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Persistence.generateSchema("pu", null);
        System.out.println("danzo");
    }
    
}
