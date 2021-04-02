package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MessageDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addMessage(Message message) {
        entityManager.persist(message);
    }

    public Message findById(long id) {
        return entityManager.find(Message.class, id);
    }

    public void update(Message message) {
        entityManager.merge(message);
    }

    public void delete(Message message) {
        entityManager.remove(entityManager.contains(message) ?
                message : entityManager.merge(message));
    }

    public List<Message> findAll(){
        Query query = entityManager.createQuery("select m from Message m");  // albo zamiast pakietowej po prostu Book
        return query.getResultList();
    }
}
