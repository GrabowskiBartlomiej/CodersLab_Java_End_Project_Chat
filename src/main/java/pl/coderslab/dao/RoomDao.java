package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoomDao {


    @PersistenceContext
    private EntityManager entityManager;

    public void addRoom(Room room) {
        entityManager.persist(room);
    }

    public Room findById(long id) {
        return entityManager.find(Room.class, id);
    }

    public void update(Room room) {
        entityManager.merge(room);
    }

    public void delete(Room room) {
        entityManager.remove(entityManager.contains(room) ?
                room : entityManager.merge(room));
    }

    public List<Room> findAll() {
        Query query = entityManager.createQuery("select r from Room r");
        return query.getResultList();
    }

}
