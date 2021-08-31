package com.megait.mymall.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("ALBUM")  //상속에서 SINGLE_TABLE 전략을 쓸 때 자식에게 넣어주는 표식 같은 것
public class Album extends Item {
    @Column(nullable = false)
    String artist;
}
