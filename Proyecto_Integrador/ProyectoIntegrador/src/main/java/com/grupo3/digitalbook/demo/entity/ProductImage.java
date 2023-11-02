    package com.grupo3.digitalbook.demo.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import lombok.*;

    import javax.persistence.*;


    @Entity
    @Table(name = "ProductImage")
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString

        public class ProductImage {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;

            @Lob
            @Column(name = "image_data")
            private byte[] productImage;
            /*private String url;*/

            @ManyToOne
            @JsonIgnore
            @JoinColumn(name = "product_id")
            private Product product;

        }
