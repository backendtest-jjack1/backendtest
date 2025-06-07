package co.kr.metacoding.backendtest.user;

import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi400;
import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse.SaveDTO saveUser(UserRequest.SaveDTO reqDTO) {
        // 1. name 체크
        Optional<User> checkUser = userRepository.findByName(reqDTO.getName());
        if (checkUser.isPresent()) {
            throw new ExceptionApi400("이미 존재하는 name 입니다");
        }

        // 2. 유저 객체 생성
        User user = reqDTO.toEntity();

        // 3. 유저 저장
        User userPS = userRepository.save(user);

        // 4. 유저 응답
        return new UserResponse.SaveDTO(userPS);
    }

    public UserResponse.DTO getUser(Integer id) {
        // 1. 유저 조회
        User userPS = userRepository.findById(id).orElseThrow(() -> new ExceptionApi404("존재하지 않는 user 입니다"));

        // 2. 유저 응답
        return new UserResponse.DTO(userPS);
    }

    @Transactional
    public UserResponse.DTO updateUser(Integer id, UserRequest.UpdateDTO reqDTO) {
        // 1. name 체크
        Optional<User> checkUser = userRepository.findByName(reqDTO.getName());
        if (checkUser.isPresent() && checkUser.get().getName().equals(reqDTO.getName())) {
            throw new ExceptionApi400("이미 존재하는 name 입니다");
        }

        // 2. 유저 조회
        User userPS = userRepository.findById(id).orElseThrow(() -> new ExceptionApi404("존재하지 않는 user 입니다"));

        // 3. 이름 수정
        userPS.updateName(reqDTO.getName());

        // 4. 유저 응답
        return new UserResponse.DTO(userPS);
    }
}
