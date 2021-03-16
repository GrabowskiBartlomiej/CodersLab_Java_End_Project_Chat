package pl.coderslab.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;
}
