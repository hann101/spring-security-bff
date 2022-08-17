package com.example.SecurityExample.service;


import com.example.SecurityExample.domain.Role;
import com.example.SecurityExample.domain.User;
import com.example.SecurityExample.repo.RoleRepo;
import com.example.SecurityExample.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    //UserDetails는 유저정보를 담는 객체
    //UserDetailsService 인터페이스는 DB에서 유저 정보를 가져오는 역할을 한다. 5.7이전 버전과 동일하게 사용됨.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User found in the database : {} ",user.getName());
        }

//        log.error("User found in the database : {}", username);

        log.info("+++++++++++++++++++++"+user.getPassword());

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {} ", roleName,username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        //role을 가져와서 새로운 role 추가
        user.getRoles().add(role);

    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {} ", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users ");

        return userRepo.findAll();
    }


}
