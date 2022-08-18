package org.example.service;

import com.spring.services.model.User;
import org.example.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    public ResponseEntity<List<User>> getAllUser();
    public ResponseEntity<User> getElementById(Integer userId);

    public ResponseEntity<Void> deleteUser(Integer userId);

    public ResponseEntity<Void> updateUser(User user);

    public ResponseEntity<Void> postUser(User user);

    public UserEntity findUserByEmail(String email);
}

