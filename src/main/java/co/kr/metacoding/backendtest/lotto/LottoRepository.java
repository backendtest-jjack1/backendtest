package co.kr.metacoding.backendtest.lotto;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LottoRepository {
    private final EntityManager em;
}
