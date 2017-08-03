package com.theraportal.config;

import com.theraportal.model.Role;
import com.theraportal.model.User;
import com.theraportal.repository.RoleRepository;
import com.theraportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// Roles
		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			roleRepository.save(new Role("ROLE_ADMIN"));
		}

        if (roleRepository.findByName("ROLE_THERAPIST") == null) {
            roleRepository.save(new Role("ROLE_THERAPIST"));
        }

        if (roleRepository.findByName("ROLE_CLIENT") == null) {
            roleRepository.save(new Role("ROLE_CLIENT"));
        }
		
		// Admin account
		if (userRepository.findByUsername("admin") == null) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setEmail("admin@gmail.com");
			admin.setPassword(passwordEncoder.encode("secret"));
			HashSet<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			roles.add(roleRepository.findByName("ROLE_THERAPIST"));
			roles.add(roleRepository.findByName("ROLE_CLIENT"));
			admin.setRoles(roles);
			userRepository.save(admin);
		}
		
		// Therapist account
        if (userRepository.findByUsername("therapist") == null) {
            User admin = new User();
            admin.setUsername("therapist");
            admin.setEmail("therapist@gmail.com");
            admin.setPassword(passwordEncoder.encode("secret"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_THERAPIST"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Client account
        if (userRepository.findByUsername("client") == null) {
            User admin = new User();
            admin.setUsername("client");
            admin.setEmail("client@gmail.com");
            admin.setPassword(passwordEncoder.encode("secret"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_CLIENT"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }
	}

}
