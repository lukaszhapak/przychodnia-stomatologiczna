package com.example.przychodnia.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 24, message = "nazwa użytkownika nie może być dłuższa niż 24 znaków")
    private String userName;
    @Size(min = 8, message = "hasło musi się składać z conajmniej 8 znaków")
    private String password;
    @Pattern(regexp = "^(.+)@(.+)$", message = "email nie prawidłowy")
    private String email;
    @Size(min = 1, message = "podaj imię")
    private String firstName;
    @Size(min = 1, message = "podaj nazwisko")
    private String lastName;
    @Pattern(regexp = "\\d{11}", message = "pesel powinien składać się z 11 cyfr")
    private String personalIdentityNumber;
    @Size(min = 1, message = "podaj miejscowość")
    private String city;
    private String street;
    @Size(min = 1, message = "podaj numer lokalu")
    private String houseNumber;
    private String flatNumber;
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "podaj kod pocztowy")
    private String postalCode;
    @Size(min = 5, message = "podaj numer telefonu")
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;
}
