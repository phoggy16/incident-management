package com.incident.incident_management.util;

import com.incident.incident_management.exception_handling.CustomException;
import com.incident.incident_management.model.User;
import com.incident.incident_management.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUtil {
    private final UserRepository userRepository;

    public UserUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        Optional<User> user=userRepository.findByEmailId(userName);
        if(user.isEmpty()){
            throw new CustomException(HttpStatus.FORBIDDEN,"You don't have access");
        }
        return user.get();
    }
}
