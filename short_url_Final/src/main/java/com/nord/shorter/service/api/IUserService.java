package com.nord.shorter.service.api;

import com.nord.shorter.model.User;

import java.util.List;

public interface IUserService {
    User findByUsername(String userName);

    User findUserById(Long userId);

    List<User> allUsers();

    boolean saveUser(User user);

    boolean deleteUser(Long userId);

    List<User> getAll();
}
