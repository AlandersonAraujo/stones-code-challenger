package br.com.alphablack.stone.model.product;

import br.com.alphablack.stone.model.seller.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer price;
    private String zipcode;
    @ManyToMany
    private Seller seller;
    private String thumbnailHd;
    private Date date;
}
