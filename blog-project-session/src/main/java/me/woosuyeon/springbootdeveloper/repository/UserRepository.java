package me.woosuyeon.springbootdeveloper.repository;

import me.woosuyeon.springbootdeveloper.domain.Article;
import me.woosuyeon.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
