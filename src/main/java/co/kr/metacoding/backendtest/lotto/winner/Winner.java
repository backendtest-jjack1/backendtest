package co.kr.metacoding.backendtest.lotto.winner;

import co.kr.metacoding.backendtest.lotto.Lotto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "winner_tb")
@Entity
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Lotto lotto;
    private Integer rank; // 1등, 2등, 3등, 4등, 5등

    @Builder
    public Winner(Integer id, Lotto lotto, Integer rank) {
        this.id = id;
        this.lotto = lotto;
        this.rank = rank;
    }
}
