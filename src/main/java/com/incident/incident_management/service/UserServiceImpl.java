package com.incident.incident_management.service;


import com.incident.incident_management.exception_handling.CustomException;
import com.incident.incident_management.model.User;
import com.incident.incident_management.repository.UserRepository;
import com.incident.incident_management.request.UserCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void registerUser(UserCreateRequest userCreateRequest) {
        Optional<User> userOptional =  userRepository.findByEmailId(userCreateRequest.emailId().trim());
        if(userOptional.isPresent()){
            throw new CustomException(HttpStatus.BAD_REQUEST,"user already exists");
        }
        User user = new User(
                userCreateRequest.name(),
                userCreateRequest.emailId(),
                userCreateRequest.phoneNumber(),
                userCreateRequest.address(),
                userCreateRequest.city(),
                userCreateRequest.country(),
                userCreateRequest.pinCode(),
                bCryptPasswordEncoder.encode(userCreateRequest.password())
        );

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmailId(emailId);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Collection<SimpleGrantedAuthority> authorites = new ArrayList<>();
        User user=userOptional.get();
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), authorites);
    }
}
