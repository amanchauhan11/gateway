package com.coviam.gateway.controller.proxy;

import com.coviam.gateway.controller.ApiRequest;
import com.coviam.gateway.entity.User;
import com.coviam.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LeaderBoard {

    @Value("${microservice.leaderboard.ip}")
    private String leaderboardip;

    @Autowired
    private UserService userService;

   @GetMapping("/leaderboard/static")
    public ResponseEntity leaderboard(@RequestParam Integer contestId, @RequestParam Integer noOfRecords){
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       User user = userService.findUserByUsername(auth.getName());
       Map<String, String> params = new HashMap<>();
       params.put("userId", Integer.toString(user.getId()));
       params.put("contestId", contestId.toString());
       params.put("noOfRecords", noOfRecords.toString());
       return ApiRequest.get(leaderboardip+"leaderboard/static", params);
    }

}