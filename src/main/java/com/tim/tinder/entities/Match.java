package com.tim.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue
    private Long idMatch;

    @ManyToOne
    private User userFrom;

    @ManyToOne
    private User userTo;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    private Boolean isMatched;
    private Date likeDate;
    private Boolean favouriteFrom;
    private Boolean favouriteTo;
}
