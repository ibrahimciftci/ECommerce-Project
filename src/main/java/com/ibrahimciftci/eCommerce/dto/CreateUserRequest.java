package com.ibrahimciftci.eCommerce.dto;

import com.ibrahimciftci.eCommerce.model.Role;
import lombok.Builder;

import java.util.Set;


@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<Role> authorities
){
}