/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import facades.UserFacade;
import java.util.List;

/**
 *
 * @author edipetres
 */
public class testDB {
    public static void main(String[] args) {
//        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();
//        
//        User user = new User("user", "test");
//        user.addRole("User");
//        User admin = new User("admin", "test");
//        admin.addRole("Admin");
//        
//        em.getTransaction().begin();
//        em.persist(user);
//        em.persist(admin);
//        em.getTransaction().commit();
//        
//        
//        Query q = em.createQuery("SELECT u from User u");
//        List<User> results = q.getResultList();
//        if (results != null) {
//            System.out.println("Users found");
//        }
//        for(User u : results){
//            System.out.println(u.getUserName());
//            for(String role : u.getRolesAsStrings()){
//                System.out.println("--> "+role);
//            }
//        }

        UserFacade facade = new UserFacade();
        List<String> u = facade.createUser("testuser", "testpassword");
        System.out.println("result "+u.get(0));
    }
}
