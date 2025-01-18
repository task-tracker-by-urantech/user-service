package com.urantech.userservice.service;

import com.urantech.userservice.model.rest.UserResponse;
import com.urantech.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getUsersWithUnfinishedTasks() {
        try {
            return userRepository.findAllEnabledUsersWithUnfinishedTasksCount();
        } catch (Exception e) {
            throw new IllegalStateException("Непредвиденная ошибка: " + e.getMessage());
        }
    }
}
