package com.tim.tinder.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    @GeneratedValue
    private Long idPhoto;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(columnDefinition = "mediumblob")
    private byte[] photo;

    public Photo(byte[] photo) {
        this.photo = photo;
    }
}
