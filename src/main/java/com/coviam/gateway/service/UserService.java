package com.coviam.gateway.service;

import com.coviam.gateway.entity.Role;
import com.coviam.gateway.entity.User;
import com.coviam.gateway.model.UserDTO;
import com.coviam.gateway.repository.RoleRepository;
import com.coviam.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByUsername(String username) {
        User user = userRepository.findByName(username);
        return user;
    }

    public void saveUser(UserDTO userin) {
        User user = new User();
        user.setEmail(userin.getEmail());
        user.setName(userin.getName());
        user.setUsername(userin.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userin.getPassword()));
        Role userRole = roleRepository.findByRole(userin.getRole());
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setActive(1);
        userRepository.save(user);
    }

}
