package com.api.recipe.entity;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "UserCredentials")
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    private boolean isAccountActive;
    private String roles;
    private String authorities;
    private boolean isAccountNonLocked;
}
