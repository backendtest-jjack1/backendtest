package co.kr.metacoding.backendtest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse.SaveDTO save(UserRequest.SaveDTO reqDTO) {
        User user = reqDTO.toEntity();
        User userPS = userRepository.save(user);
        return new UserResponse.SaveDTO(userPS);
    }

    public UserResponse.DTO getUser(Integer id) {
        User userPS = userRepository.findById(id);
        return new UserResponse.DTO(userPS);
    }

    @Transactional
    public UserResponse.UpdateDTO update(Integer id, UserRequest.UpdateDTO reqDTO) {
        User userPS = userRepository.findById(id);
        userPS.updateName(reqDTO.getName());
        return new UserResponse.UpdateDTO(userPS);
    }
}
