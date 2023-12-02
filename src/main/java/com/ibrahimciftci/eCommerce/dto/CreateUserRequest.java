package com.ibrahimciftci.eCommerce.dto;

import com.ibrahimciftci.eCommerce.model.Gender;
import com.ibrahimciftci.eCommerce.model.Role;
import lombok.Builder;

import java.util.Date;
import java.util.Set;


@Builder
public record CreateUserRequest(
        String name,
        String lastName,
        String email,
        String username,
        String password,
        String gender,
        Date birthDate,
        Set<Role> authorities

        ){

        public Gender getGenderEnum() {
                return Gender.fromValue(this.gender);
        }

}