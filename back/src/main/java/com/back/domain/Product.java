package com.back.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@ToString(exclude = "imageList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;
    private String pname;
    private int price;
    private String pdesc;
    private boolean delFlag;
    public void changeDel(boolean delFlag) {
        this.delFlag = delFlag;
    }

    @ElementCollection
    @Builder.Default
    private List<ProductImage> imageList = new ArrayList<>();
    public void changePrice(int price) {
        this.price = price;
    }
    public void changeDesc(String desc){
        this.pdesc = desc;
    }

    }
    public void clearList() {
        this.imageList.clear();
    }
}