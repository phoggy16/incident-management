package com.incident.incident_management.service;

import com.incident.incident_management.request.UserLoginRequest;
import com.incident.incident_management.response.UserLoginResponse;
import com.incident.incident_management.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    private final HttpServletRequest httpServletRequest;

    public LoginServiceImpl(AuthenticationManager authenticationManager, JWTUtil jwtUtil, HttpServletRequest httpServletRequest) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.emailId(), userLoginRequest.password()));

        String requestURL = httpServletRequest.getRequestURL().toString();

        String accessToken = jwtUtil.generateJWTToken(userLoginRequest.emailId(), requestURL, new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        String refreshToken = jwtUtil.generateJWTToken(userLoginRequest.emailId(), requestURL, new Date(System.currentTimeMillis() + 1000 * 60 * 1000));

        return new UserLoginResponse(accessToken,refreshToken);
    }
}
