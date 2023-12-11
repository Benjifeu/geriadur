package com.example.geriadur.repositories;

import com.example.geriadur.domain.Etymon;
import com.example.geriadur.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User>  findByFirstName(String firstName);

}
