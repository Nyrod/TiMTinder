package com.tim.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long idUser;

    private String name;
    private String surname;
    private String phone;
    private Double lon;
    private Double lat;
    private Date birthday;
    private String description;
    private String login;
    private String token;
    private Boolean isAdmin;
}
