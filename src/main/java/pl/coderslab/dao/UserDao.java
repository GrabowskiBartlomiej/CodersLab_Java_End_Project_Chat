package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Repository
@Transactional
public class UserDao {
    private static Pattern CHECK_PASSWORD = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z]).{6,20}$");

    @PersistenceContext
    private EntityManager entityManager;

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ?
                user : entityManager.merge(user));
    }

    public static String hashPassword(String password){
        return BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }

    public static boolean validatePassword(String password){
        Matcher matcher = CHECK_PASSWORD.matcher(password);
        return matcher.matches();
    }


    public List<User> findAll(){
        Query query = entityManager.createQuery("select u from User u");  // albo zamiast pakietowej po prostu Book
        return query.getResultList();
    }

    public User login(String userEmail,String userPassword){
        List<User> allUsers = findAll();
        for (User user: allUsers) {
            if(user.getEmail().equalsIgnoreCase(userEmail)){
                System.out.println("true "+ user.getEmail());
                if(BCrypt.checkpw(userPassword,user.getPassword())){
                    return user;
                }

            }
        }
        return null;
    }


    public List<User> findAllUsersOnTheServer(long id){
        Query query = entityManager.createQuery("select u from User u join u.rooms r where r.id =: id");
        query.setParameter("id",id);
        return query.getResultList();
    }

    public List<User> findAllUsersOnline(){
        Query query = entityManager.createQuery("select u from User u where not u.status = 0");
        return query.getResultList();
    }

    public List<User> findAllUsersOffline(){
        Query query = entityManager.createQuery("select u from  User u where u.status = 0");
        return query.getResultList();
    }
}
