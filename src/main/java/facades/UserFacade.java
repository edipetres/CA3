package facades;

import security.IUserFacade;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import security.IUser;
import security.PasswordStorage;

public class UserFacade implements IUserFacade {

    /*When implementing your own database for this seed, you should NOT touch any of the classes in the security folder
    Make sure your new facade implements IUserFacade and keeps the name UserFacade, and that your Entity User class implements 
    IUser interface, then security should work "out of the box" with users and roles stored in your database */
    public UserFacade() {
        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();

        User user;
        try {
            user = new User("user", PasswordStorage.createHash("test"));
            user.addRole("User");
            User admin = new User("admin", PasswordStorage.createHash("test"));
            admin.addRole("Admin");

            em.getTransaction().begin();
            em.persist(user);
            em.persist(admin);
            em.getTransaction().commit();
            
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public IUser getUserByUserId(String username) {
        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u where u.userName = :username");
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }
    }

    /*
  Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password) {

        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u where u.userName = :username");
        query.setParameter("username", userName);
        List<User> result = query.getResultList();

        IUser user = result.get(0);
        try {
            //return user != null && user.getPassword().equals(password) ? user.getRolesAsStrings() : null;
            if (user != null && PasswordStorage.verifyPassword(password, user.getPassword())) {
                return user.getRolesAsStrings();
            }
            else
                return null;
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            System.out.println("Exception in authenticatUser. "+ex.toString());
            return null;
        }
        
    }

    //Creates new user, returning user roles if successful, null if not
    @Override
    public List<String> createUser(String userName, String password) {
        EntityManager em = Persistence.createEntityManagerFactory("pu", null).createEntityManager();

        User user;
        try {
            user = new User(userName, PasswordStorage.createHash(password));
            user.addRole("User");

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            
            return authenticateUser(userName, password);
            
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
