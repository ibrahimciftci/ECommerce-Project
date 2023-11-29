package com.ibrahimciftci.eCommerce.dto;

import com.ibrahimciftci.eCommerce.model.Gender;
import com.ibrahimciftci.eCommerce.model.Role;
import com.ibrahimciftci.eCommerce.model.User;
import lombok.Builder;


import java.time.LocalDate;
import java.util.Set;

@Builder
public record UserDTO (
         Long id,
         String email,
         String username,
         String password,
         String firstName,
         String lastName,
         LocalDate birthDate,
         Gender gender,
         boolean accountNonExpired,
         boolean isEnabled,
         boolean accountNonLocked,
         boolean credentialsNonExpired,
         Set<Role> authorities
){

    public static UserDTO fromEntity(User user){
        return new UserDTO(user.getId(), user.getEmail(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user
                .getBirthDate(), user.getGender(), user.isAccountNonExpired(), user.isEnabled(),user.isAccountNonLocked(), user.isCredentialsNonExpired(), user.getAuthorities());
    }
}