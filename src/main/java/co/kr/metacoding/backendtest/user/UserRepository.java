package co.kr.metacoding.backendtest.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    public User findByName(String name) {
        Query query = em.createQuery("select u from User u where u.name = :name", User.class);
        query.setParameter("name", name);
        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
