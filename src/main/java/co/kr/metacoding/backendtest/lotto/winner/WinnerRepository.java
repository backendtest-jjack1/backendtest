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
     * JDBC를 사용해 insert를 한번에 묶어서 실행
     *
     * @param winners
     */
    public void saveAllWithJdbc(List<Winner> winners) {
        if (winners.isEmpty()) return;

        String sql = "INSERT INTO winner_tb (lotto_id, rank) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql, winners, winners.size(), (ps, winner) -> {
            ps.setInt(1, winner.getLotto().getId());
            ps.setInt(2, winner.getRank());
        });
    }
}
