package com.incident.incident_management.controller;

import com.incident.incident_management.request.UserCreateRequest;
import com.incident.incident_management.request.UserLoginRequest;
import com.incident.incident_management.response.SuccessMessageResponse;
import com.incident.incident_management.response.UserLoginResponse;
import com.incident.incident_management.service.LoginService;
import com.incident.incident_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external/v1/user")
@Validated
public class UserController {
    private final LoginService loginService;

    private final UserService userService;

    public UserController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }
    @PostMapping("/login")
    public UserLoginResponse loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest){
        return loginService.loginUser(userLoginRequest);
    }

    @PostMapping("/register")
    public SuccessMessageResponse registerUser(@Valid @RequestBody UserCreateRequest userCreateRequest){
       userService.registerUser(userCreateRequest);
       return new SuccessMessageResponse("User Created Successfully");
    }
}
