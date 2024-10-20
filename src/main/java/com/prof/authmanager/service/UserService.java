package com.prof.authmanager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prof.authmanager.dto.RoleDTO;
import com.prof.authmanager.dto.UserDTO;
import com.prof.authmanager.entity.Role;
import com.prof.authmanager.entity.User;
import com.prof.authmanager.repository.RoleRepository;
import com.prof.authmanager.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private RoleRepository roleRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Transactional
	    public void registerUser(UserDTO userDTO) {
	    	  // Create a new user entity
	        User newUser = new User();
	        newUser.setUsername(userDTO.getUsername());
	        newUser.setEmail(userDTO.getEmail());
	        newUser.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
	        newUser.setCreatedAt(LocalDateTime.now());
	        newUser.setUpdatedAt(LocalDateTime.now());
	        newUser.setActive(true);
	        newUser.setVerified(true);

	        // Fetch or create roles and associate them with the user
	        Set<Role> roles = new HashSet<>();
	        for (Role roleDTO : userDTO.getRoles()) {
	            Role role = roleRepository.findByName(roleDTO.getName())
	                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleDTO.getName()));
	            roles.add(role);
	        }
	        newUser.setRoles(roles);

	        userRepository.save(newUser);
	    }

	    }
    
