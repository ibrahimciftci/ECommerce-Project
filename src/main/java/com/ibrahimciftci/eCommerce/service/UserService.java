package com.ibrahimciftci.eCommerce.service;

import com.ibrahimciftci.eCommerce.dto.UserDTO;
import com.ibrahimciftci.eCommerce.model.User;
import com.ibrahimciftci.eCommerce.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User createUser(UserDTO userDTO){
        User newUser = User.builder()
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .email(userDTO.email())
                .username(userDTO.username())
                .password(passwordEncoder.encode(userDTO.password()))
                .birthDate(userDTO.birthDate())
                .gender(userDTO.gender())
                .accountNonExpired(userDTO.accountNonExpired())
                .isEnabled(userDTO.isEnabled())
                .accountNonLocked(userDTO.accountNonLocked())
                .credentialsNonExpired(userDTO.credentialsNonExpired())
                .authorities(userDTO.authorities())
                .build();

        return userRepository.save(newUser);
    }

















/*

    public String loginUser(LoginDTO loginDTO) {
        // Kullanıcı adı ve şifre kontrolü
        // JWT oluşturulup döndürülmesi
        return null;
    }

    public void resetPassword(String email) {
        // E-posta adresine şifre sıfırlama bağlantısı gönderme işlemi
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Diğer kullanıcı işlemleri metotları...

 */
}