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
public class ECommerceApplication implements CommandLineRunner {

	private final UserService userService;

	public ECommerceApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createDummyData();
	}

	private void createDummyData(){
		UserDTO user = UserDTO.builder()
				.firstName("mehmet")
				.lastName("can")
				.email("mehmet@gmail.com")
				.birthDate(LocalDate.of(1990, 5, 15))
				.username("mehmet")
				.password("mehmet")
				.gender(Gender.MALE)
				.accountNonExpired(true)
				.isEnabled(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.authorities(Set.of(Role.ROLE_USER))
				.build();
		userService.createUser(user);

		UserDTO admin = UserDTO.builder()
				.firstName("selim")
				.lastName("can")
				.email("selim@gmail.com")
				.birthDate(LocalDate.of(2000, 7, 20))
				.username("selim")
				.password("selim")
				.gender(Gender.MALE)
				.accountNonExpired(true)
				.isEnabled(true)
				.accountNonLocked(true)
				.credentialsNonExpired(true)
				.authorities(Set.of(Role.ROLE_ADMIN))
				.build();

		userService.createUser(admin);
	}
}
