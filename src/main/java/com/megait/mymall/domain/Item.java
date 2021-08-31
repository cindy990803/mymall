package com.megait.mymall.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/*
*   < DB에 있어서의 상속에 대한 전략 >
*   JOINED : Item(부모), Book(자식), Album(자식) 테이블을 각각 만들고 (외래키 사용) JOIN으로 조회
*   SINGLE_TABLE : Item(부모) 테이블 한 개만 만들고, 자식 필드까지 컬럼을 만든다.
*   TABLE_PER_CLASS : Book(자식), Album(자식) 테이블을 만든다 (부모 필드까지 다)
*/
@DynamicInsert  //필드값이 null인 경우는 Insert, Update할 때 영향을 받지 않음
@DynamicUpdate  //
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Item {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ColumnDefault("0")
    private int price;

    @ColumnDefault("100")
    private int stackQuantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;

    @Column(nullable = false)
    private String imageUrl;
    
    @ManyToMany(mappedBy = "likes", cascade = CascadeType.ALL)
    private List<Member> likers;  //이 상품을 '좋아요'한 사람들
    
    @ManyToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Member> owners;  //이 상품을 장바구니에 넣은 사람들
    
    // ** ManyToMany는 권장되지 않음. 중간 엔티티(테이블)를 따로 만드는 것이 좋음.

    @PostLoad
    public void createList() {
        if (likers ==null) {
            likers = new ArrayList<>();
        }
        if (owners ==null) {
            owners = new ArrayList<>();
        }
    }
}
