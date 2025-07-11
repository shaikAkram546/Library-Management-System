package com.library_management.Library_Management_System.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library_management.Library_Management_System.model.CustomUserDetails;
import com.library_management.Library_Management_System.model.User;
import com.library_management.Library_Management_System.repos.UserRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

  @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
    return new CustomUserDetails(user);
}


}
