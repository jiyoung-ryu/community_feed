package kr.co.bullets.user.application.interfaces;

import kr.co.bullets.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);
}
