package kr.co.bullets.user.application;

import kr.co.bullets.user.application.dto.CreateUserRequestDto;
import kr.co.bullets.user.application.interfaces.UserRepository;
import kr.co.bullets.user.domain.User;
import kr.co.bullets.user.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo userInfo = new UserInfo(dto.userName(), dto.userProfileUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
