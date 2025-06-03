package co.kr.metacoding.backendtest.lotto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LottoController {
    private final LottoService lottoService;

    @PostMapping("/lottos")
    public ResponseEntity<?> generateLottoNumbers() {
        LottoResponse.DTO respDTO = lottoService.generateLottoNumbers();
        return ResponseEntity.ok(respDTO);
    }
}
