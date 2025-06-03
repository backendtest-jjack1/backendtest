package co.kr.metacoding.backendtest.lotto;

import co.kr.metacoding.backendtest._core.utils.Resp;
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
        return Resp.ok(respDTO);
    }
}
