package com.steve.Picgo.entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    String lastName;

    String username;

    @Column(unique = true, nullable = false)
    String email;

    String password;

    String dateOfBirth;

    int gender;

    String avatar;

    String cover;

    @Column(length = 500)
    String bio;

    String role = "ROLE_USER";

    boolean enabled;
}

