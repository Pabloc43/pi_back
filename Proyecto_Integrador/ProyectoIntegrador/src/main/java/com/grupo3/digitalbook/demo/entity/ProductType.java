package com.grupo3.digitalbook.demo.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProductType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "productType")
    private List<Product> products = new ArrayList<>();


    public ProductType(String description) {
        this.description = description;
    }

}






