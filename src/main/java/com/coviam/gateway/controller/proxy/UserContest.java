package com.coviam.gateway.controller.proxy;

import com.coviam.gateway.controller.ApiRequest;
import com.coviam.gateway.entity.User;
import com.coviam.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserContest {

    @Value("${usercontest.contest.ip}")
    private String usercontestIp;

    @Autowired
    private UserService userService;

    @GetMapping("/usercontest/useractive")
    public ResponseEntity useractive(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Map<String, String> params = new HashMap<>();
        params.put("userId", Integer.toString(user.getId()));
        return ApiRequest.get(usercontestIp+"usercontest/useractive", params);
    }

    @PostMapping("/usercontest/userresult")
    public ResponseEntity userresult(@RequestBody Map<String, Object> request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        request.put("userId", Integer.toString(user.getId()));
        request.put("username", user.getUsername());
        return ApiRequest.post(usercontestIp+"usercontest/userresult", request);
    }

    @PostMapping("/response/user")
    public ResponseEntity submitrespone(@RequestBody Map<String, Object> request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        request.put("userId", Integer.toString(user.getId()));
        request.put("username", user.getUsername());
         return ApiRequest.post(usercontestIp+"response/user", request);
    }

    @PutMapping("/response/user")
    public ResponseEntity modifyresponse(@RequestBody Map<String, Object> request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        request.put("userId", Integer.toString(user.getId()));
        request.put("username", user.getUsername());
        return ApiRequest.put(usercontestIp+"response/user", request);
    }
}
