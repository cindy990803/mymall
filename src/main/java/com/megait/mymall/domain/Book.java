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
@DiscriminatorValue("BOOK")
public class Book extends Item {
    @Column(nullable = false)
    String isbn;  //고유식별번호(?)

}
