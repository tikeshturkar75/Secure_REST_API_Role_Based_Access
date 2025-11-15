package com.mindprove.midprove.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindprove.midprove.Entity.User;
import com.mindprove.midprove.repository.UserRepository;

import jakarta.annotation.PostConstruct; 
import org.springframework.security.core.authority.SimpleGrantedAuthority; 

@Service("customUserDetailsService") 
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) 
                .authorities(new SimpleGrantedAuthority("ROLE_" + user.getRole()))  
                .build();
    }

    @PostConstruct
    public void seedUsers() {
        if (userRepo.findByUsername("admin").isEmpty()) {
            userRepo.save(new User("admin", encoder.encode("adminpass"), "ADMIN"));
        }
        if (userRepo.findByUsername("john").isEmpty()) {
            userRepo.save(new User("john", encoder.encode("johnpass"), "USER"));
        }
        System.out.println("Users seeded: admin & john"); 
    }
}