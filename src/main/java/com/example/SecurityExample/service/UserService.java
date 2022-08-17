package com.example.SecurityExample.service;


import com.example.SecurityExample.domain.Role;
import com.example.SecurityExample.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);

    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
}
