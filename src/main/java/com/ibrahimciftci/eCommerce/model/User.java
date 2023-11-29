package com.ibrahimciftci.eCommerce.model;

import com.ibrahimciftci.eCommerce.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;

    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role",  nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;


    public static User fromDto(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.id());
        user.setEmail(userDTO.email());
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setBirthDate(userDTO.birthDate());
        user.setGender(userDTO.gender());
        user.setAccountNonExpired(userDTO.accountNonExpired());
        user.setEnabled(userDTO.isEnabled());
        user.setAccountNonLocked(userDTO.accountNonLocked());
        user.setCredentialsNonExpired(userDTO.credentialsNonExpired());
        user.setAuthorities(userDTO.authorities());
        return user;
    }
}