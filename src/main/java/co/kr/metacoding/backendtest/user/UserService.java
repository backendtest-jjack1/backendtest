package co.kr.metacoding.backendtest.user;

import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi400;
import co.kr.metacoding.backendtest._core.error.ex.ExceptionApi404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse.SaveDTO save(UserRequest.SaveDTO reqDTO) {
        User checkUser = userRepository.findByName(reqDTO.getName());
        if (checkUser != null) {
            throw new ExceptionApi400("이미 존재하는 name 입니다");
        }
        User user = reqDTO.toEntity();
        User userPS = userRepository.save(user);
        return new UserResponse.SaveDTO(userPS);
    }

    public UserResponse.DTO getUser(Integer id) {
        User userPS = userRepository.findById(id);
        if (userPS == null) {
            throw new ExceptionApi404("존재하지 않는 user 입니다");
        }
        return new UserResponse.DTO(userPS);
    }

    @Transactional
    public UserResponse.UpdateDTO update(Integer id, UserRequest.UpdateDTO reqDTO) {
        User checkUser = userRepository.findByName(reqDTO.getName());
        if (checkUser != null && checkUser.getName().equals(reqDTO.getName())) {
            throw new ExceptionApi400("이미 존재하는 name 입니다");
        }
        User userPS = userRepository.findById(id);
        userPS.updateName(reqDTO.getName());
        return new UserResponse.UpdateDTO(userPS);
    }
}
