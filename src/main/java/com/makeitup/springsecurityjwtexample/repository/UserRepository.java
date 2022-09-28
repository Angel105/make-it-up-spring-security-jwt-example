package com.makeitup.springsecurityjwtexample.repository;

import com.makeitup.springsecurityjwtexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
}
