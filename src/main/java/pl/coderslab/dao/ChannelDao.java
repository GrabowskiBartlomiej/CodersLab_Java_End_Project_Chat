package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Channel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public class ChannelDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addChannel(Channel channel) {
        entityManager.persist(channel);
    }

    public Channel findById(long id) {
        return entityManager.find(Channel.class, id);
    }

    public void update(Channel channel) {
        entityManager.merge(channel);
    }

    public void delete(Channel channel) {
        entityManager.remove(entityManager.contains(channel) ?
                channel : entityManager.merge(channel));
    }

    public List<Channel> findAll() {
        Query query = entityManager.createQuery("select c from Channel c");
        return query.getResultList();
    }
}
