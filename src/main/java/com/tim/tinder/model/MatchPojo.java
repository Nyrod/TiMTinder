package com.tim.tinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchPojo {

    private Long idMatch;
    private Long idUser;
    private Boolean isFavourite;
    private Date likeDate;

}
