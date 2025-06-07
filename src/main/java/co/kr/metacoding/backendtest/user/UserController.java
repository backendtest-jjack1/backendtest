package co.kr.metacoding.backendtest.user;

import co.kr.metacoding.backendtest._core.log.anno.LogUserAgent;
import co.kr.metacoding.backendtest._core.utils.Resp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @LogUserAgent
    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest.SaveDTO reqDTO) {
        UserResponse.SaveDTO respDTO = userService.saveUser(reqDTO);
        return Resp.ok(respDTO);
    }

    @LogUserAgent
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        UserResponse.DTO respDTO = userService.getUser(id);
        return Resp.ok(respDTO);
    }

    @LogUserAgent
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserRequest.UpdateDTO reqDTO) {
        UserResponse.DTO respDTO = userService.updateUser(id, reqDTO);
        return Resp.ok(respDTO);
    }
}
