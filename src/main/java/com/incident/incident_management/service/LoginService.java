package com.incident.incident_management.service;

import com.incident.incident_management.request.UserLoginRequest;
import com.incident.incident_management.response.UserLoginResponse;

public interface LoginService {
    UserLoginResponse loginUser(UserLoginRequest userLoginRequest);
}
