package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long id);

    public List<User> allUsers();

    public void deleteUser(Long id);

    public User addUsers(User user);

    public void update(User user, Long id);

    public User findByName(String name);
}
