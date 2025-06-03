package co.kr.metacoding.backendtest.lotto.winner;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class WinnerRepository {
    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;

    public List<Winner> findAll() {
        return em.createQuery("SELECT w FROM Winner w", Winner.class)
                .getResultList();
    }

    /**
     * JDBC를 사용하여 Winner 목록을 일괄 저장합니다.
     * <p>
     * - 빈 리스트가 들어오면 저장을 수행하지 않습니다.
     * <p>
     * - batchUpdate를 통해 성능을 향상시킵니다.
     *
     * @param winners 저장할 Winner 목록
     */
    public void saveAllWithJdbc(List<Winner> winners) {
        if (winners.isEmpty()) return;

        String sql = "INSERT INTO winner_tb (lotto_id, rank) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(
                sql,
                winners,
                winners.size(),
                (ps, winner) -> {
                    ps.setInt(1, winner.getLotto().getId());
                    ps.setInt(2, winner.getRank());
                }
        );
    }
}
