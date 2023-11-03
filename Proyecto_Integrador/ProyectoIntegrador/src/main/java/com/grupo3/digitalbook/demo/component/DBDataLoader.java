package com.grupo3.digitalbook.demo.component;

import com.grupo3.digitalbook.demo.entity.Brand;
import com.grupo3.digitalbook.demo.entity.Spec;
import com.grupo3.digitalbook.demo.entity.ProductType;
import com.grupo3.digitalbook.demo.repository.IBrandRepository;
import com.grupo3.digitalbook.demo.repository.ISpecRepository;
import com.grupo3.digitalbook.demo.repository.IProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DBDataLoader {

    @Autowired
    private IProductTypeRepository productTypeRepository;
    @Autowired
    private IBrandRepository brandRepository;

    @Autowired
    private ISpecRepository specRepository;

    @PostConstruct
    public void init() {
        if (productTypeRepository.count() == 0) {
            productTypeRepository.saveAll(List.of(
                    new ProductType("Agrícola/Forestal"),
                    new ProductType("Construcción"),
                    new ProductType("Carga útil"),
                    new ProductType("Infraestructura")
            ));
        }

        if (brandRepository.count() == 0) {
            brandRepository.saveAll(List.of(
                    new Brand("John Deere"),
                    new Brand("Case IH"),
                    new Brand("New Holland"),
                    new Brand("Massey Ferguson"),
                    new Brand("Kubota, Deutz-Fahr"),
                    new Brand("Caterpillar"),
                    new Brand("Komatsu"),
                    new Brand("Volvo"),
                    new Brand("Hitachi"),
                    new Brand("Liebherr"),
                    new Brand("JCB"),
                    new Brand("Bobcat")
            ));
        }

        if (specRepository.count() == 0) {
            specRepository.saveAll(List.of(
                    new Spec("Motor Diesel"),
                    new Spec("Carga: 5 Toneladas"),
                    new Spec("GPS"),
                    new Spec("Pala acoplable"),
                    new Spec("Rines 26")
            ));
        }
    }
}

