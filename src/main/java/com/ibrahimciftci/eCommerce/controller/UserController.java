package com.ibrahimciftci.eCommerce.controller;

import com.ibrahimciftci.eCommerce.dto.AuthRequest;
import com.ibrahimciftci.eCommerce.dto.CreateUserRequest;
import com.ibrahimciftci.eCommerce.model.User;
import com.ibrahimciftci.eCommerce.service.JwtService;
import com.ibrahimciftci.eCommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService service;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public UserController(UserService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/addNewUser")
    public ResponseEntity<User> addUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(service.createUser(request));
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.username());
        }
        log.info("invalid username " + request.username());
        throw new UsernameNotFoundException("invalid username {} " + request.username());
    }

    @GetMapping("/userList")
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(service.findUserById(id));

    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(service.updateUser(id,request));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}