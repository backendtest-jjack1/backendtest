package co.kr.metacoding.backendtest.winner;

import jakarta.persistence.*;
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
    private Integer lottoId;
    private String rank; // 1등, 2등, 3등, 4등, 5등
}
