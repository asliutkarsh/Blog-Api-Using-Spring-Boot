package com.utkarsh.blogappapis;

import com.utkarsh.blogappapis.config.AppConstants;
import com.utkarsh.blogappapis.entity.Role;
import com.utkarsh.blogappapis.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(this.passwordEncoder.encode("xyz"));

		try {
			Role adminRole = new Role(AppConstants.ADMIN_USER,"ROLE_ADMIN");
			Role normalRole = new Role(AppConstants.NORMAL_USER,"ROLE_NORMAL");

			List<Role> roles = List.of(adminRole,normalRole);
			List<Role> result = roleRepo.saveAll(roles);

			result.forEach(role -> {
				System.out.println(role.getName());
			});

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	}
