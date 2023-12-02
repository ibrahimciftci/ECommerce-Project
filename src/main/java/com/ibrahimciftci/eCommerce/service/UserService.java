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

        User user = findUserById(id);
        user.setName(request.name());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setGender(request.getGenderEnum());
        user.setBirthDate(request.birthDate());
        user.setAuthorities(request.authorities());

        return userRepository.save(user);
    }


    public void deleteById(Long id) {
        User user = findUserById(id);
        userRepository.deleteById(id);
    }
}