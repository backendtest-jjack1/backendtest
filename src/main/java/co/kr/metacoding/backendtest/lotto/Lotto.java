package co.kr.metacoding.backendtest.lotto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lotto_tb")
@NoArgsConstructor
@Getter
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_1")
    private Integer number1;

    @Column(name = "number_2")
    private Integer number2;

    @Column(name = "number_3")
    private Integer number3;

    @Column(name = "number_4")
    private Integer number4;

    @Column(name = "number_5")
    private Integer number5;

    @Column(name = "number_6")
    private Integer number6;

    @Builder
    public Lotto(Integer id, Integer number1, Integer number2, Integer number3, Integer number4, Integer number5, Integer number6) {
        this.id = id;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
        this.number6 = number6;
    }
}
