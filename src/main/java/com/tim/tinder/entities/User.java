package com.tim.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long idUser;

    @OneToMany
    private List<Interest> interests;

    @OneToMany
    private List<Photo> photos;

    @OneToMany(mappedBy = "userFrom")
    private List<Match> matchesGiven;

    @OneToMany(mappedBy = "userTo")
    private List<Match> matchesReceived;

    @OneToOne
    private Photo avatar;

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
    private String sex;
    private String lookingFor;
}
