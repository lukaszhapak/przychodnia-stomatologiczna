package com.example.przychodnia.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private String simplifiedRoleName;

    public Role(String roleName, String simplifiedRoleName) {
        this.roleName = roleName;
        this.simplifiedRoleName = simplifiedRoleName;
    }
}
