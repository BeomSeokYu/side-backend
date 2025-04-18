package com.blws.side.user.repository;

import com.blws.side.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(Long id);

    Optional<User> findByEmail(String Email);

}
