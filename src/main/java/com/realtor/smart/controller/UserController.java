package com.realtor.smart.controller;

import com.realtor.smart.domain.User;
import com.realtor.smart.service.impl.UserServiceImpl;
import com.realtor.smart.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.realtor.smart.util.Constants.AUTHORIZATION;
// Для регистрации и логина пользователя

@RestController
@RequestMapping("app")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final JwtTokenUtil tokenUtil;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, JwtTokenUtil tokenUtil) {
        this.userServiceImpl = userServiceImpl;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletRequest request){


        ResponseEntity<String> responseEntity = ResponseEntity.ok("User " + user.getName() +
                " with token " +  tokenUtil.generateToken(user)  + " is logged!");

        if (!userServiceImpl.existsUser(user)) {
            responseEntity = new ResponseEntity<>("Register, please", HttpStatus.FORBIDDEN);
        }

        return responseEntity;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        ResponseEntity<String> responseEntity = null;

        if (!userServiceImpl.register(user)) {
            responseEntity = new ResponseEntity<>("User already register", HttpStatus.CONFLICT);
        } else {
            responseEntity = ResponseEntity.ok("User " + user + " is registered!");
        }

        return responseEntity;
    }
}
