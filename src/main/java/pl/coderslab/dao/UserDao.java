package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addAuthor(User user) {
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

    public List<User> findAll(){
        Query query = entityManager.createQuery("select u from User u");  // albo zamiast pakietowej po prostu Book
        return query.getResultList();
    }
}
