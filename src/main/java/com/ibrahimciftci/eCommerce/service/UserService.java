package com.ibrahimciftci.eCommerce.service;

import com.ibrahimciftci.eCommerce.dto.CreateUserRequest;
import com.ibrahimciftci.eCommerce.model.User;
import com.ibrahimciftci.eCommerce.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    public User createUser(CreateUserRequest request) {

        User newUser = User.builder()
                .name(request.name())
                .lastName(request.lastName())
                .email(request.email())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .gender(request.getGenderEnum())
                .birthDate(request.birthDate())
                .authorities(request.authorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();

        return userRepository.save(newUser);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public User updateUser(Long id, CreateUserRequest request) {

        User existingUser = findUserById(id);
        existingUser.setName(request.name());
        existingUser.setLastName(request.lastName());
        existingUser.setEmail(request.email());
        existingUser.setUsername(request.username());
        existingUser.setPassword(passwordEncoder.encode(request.password()));
        existingUser.setGender(request.getGenderEnum());
        existingUser.setBirthDate(request.birthDate());
        existingUser.setAuthorities(request.authorities());

        return userRepository.save(existingUser);
    }


    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}