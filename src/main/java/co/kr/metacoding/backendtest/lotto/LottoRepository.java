package co.kr.metacoding.backendtest.lotto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class LottoRepository {
    private final EntityManager em;

    public Lotto save(Lotto lotto) {
        em.persist(lotto);
        return lotto;
    }

    public List<Lotto> findAll() {
        Query query = em.createQuery("SELECT l FROM Lotto l", Lotto.class);
        return query.getResultList();
    }
}
