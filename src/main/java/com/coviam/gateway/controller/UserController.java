package com.coviam.gateway.controller;

import com.coviam.gateway.entity.User;
import com.coviam.gateway.model.UserDTO;
import com.coviam.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path="/api")
    public String testapi(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        return "Id is "+user.getId();
    }

    @PostMapping(path = "/signup", consumes = "application/json")
    public ResponseEntity signup(@RequestBody UserDTO userDTO){
        try {
            userService.saveUser(userDTO);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Bean
    public RestTemplate restTemplateGen(){
        return new RestTemplate();
    }

}
