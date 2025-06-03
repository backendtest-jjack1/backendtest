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

}
