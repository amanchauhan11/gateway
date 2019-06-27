package com.coviam.gateway.controller.proxy;


import com.coviam.gateway.controller.ApiRequest;
import com.coviam.gateway.entity.User;
import com.coviam.gateway.model.UserDTO;
import com.coviam.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contest")
public class Contest {


    @Value("${microservice.contest.ip}")
    private String contestIp;

    @Autowired
    private UserService userService;

    @GetMapping("/contestofuser")
    public ResponseEntity contestofUser(@RequestParam Integer contestId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        Map<String, String> params = new HashMap<>();
        params.put("userId", Integer.toString(user.getId()));
        params.put("contestId", contestId.toString());
        return ApiRequest.get(contestIp+"contestofuser", params);
    }
/*
    @GetMapping(path = "/getdetails/{type}")
    public ResponseEntity getAllContests(@PathVariable String type) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", type);
        return ApiRequest.get(contestIp+"contests", params);
    }

    @PostMapping("/addcontest")
    public ResponseEntity createContest(@RequestBody Object requestbody) {
        return ApiRequest.post(contestIp+"addContest", requestbody);
    }

    @PostMapping("/addcontestquestion")
    public ResponseEntity addQuestion(@RequestBody Object requestbody) {
        return ApiRequest.post(contestIp, requestbody);
    }

    @DeleteMapping("/dynamic/deletequestion")
    public ResponseEntity deleteQuestion(Integer questionId) {
        Map<String, String> params = new HashMap<>();
        return ApiRequest.delete(contestIp, params);
    }
 */
}
