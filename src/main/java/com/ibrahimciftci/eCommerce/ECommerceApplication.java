package com.ibrahimciftci.eCommerce;

import com.ibrahimciftci.eCommerce.dto.UserDTO;
import com.ibrahimciftci.eCommerce.model.Gender;
import com.ibrahimciftci.eCommerce.model.Role;
import com.ibrahimciftci.eCommerce.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class ECommerceApplication{


	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
