package com.tim.tinder.model;

import com.tim.tinder.entities.Interest;
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

    private List<Interest> interests;

    @Override
    public String toString() {
        return "UserPojo{" +
                "idUser=" + idUser +
                ", photos=" + photos +
                ", avatar=" + avatar +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", description='" + description + '\'' +
                ", isAdmin=" + isAdmin +
                ", sex='" + sex + '\'' +
                ", lookingFor='" + lookingFor + '\'' +
                '}';
    }
}
