package com.incident.incident_management.service;

import com.incident.incident_management.request.UserCreateRequest;

public interface UserService {
    void registerUser(UserCreateRequest userCreateRequest);
}
