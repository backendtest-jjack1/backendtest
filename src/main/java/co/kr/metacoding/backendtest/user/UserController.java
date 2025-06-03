package co.kr.metacoding.backendtest.user;

import co.kr.metacoding.backendtest._core.log.anno.LogUserAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @LogUserAgent
    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody UserRequest.SaveDTO reqDTO) {
        UserResponse.SaveDTO respDTO = userService.save(reqDTO);
        return ResponseEntity.ok(respDTO);
    }

    @LogUserAgent
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        UserResponse.DTO respDTO = userService.getUser(id);
        return ResponseEntity.ok(respDTO);
    }

    @LogUserAgent
    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UserRequest.UpdateDTO reqDTO) {
        UserResponse.UpdateDTO respDTO = userService.update(id, reqDTO);
        return ResponseEntity.ok(respDTO);
    }
}
