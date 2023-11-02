package com.grupo3.digitalbook.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Spec")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Spec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String description;

    @ManyToMany(mappedBy = "specs")
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Spec(String description) {
        this.description = description;
    }
}
