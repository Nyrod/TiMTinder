package com.tim.tinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {

    private Long idUser;

    private List<Long> photos;

    private Long avatar;

    private String name;

    private String surname;

    private String phone;

    private Date birthday;

    private String description;

    private Boolean isAdmin;

    private String sex;

    private String lookingFor;
}
