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
    @ManyToOne
    private Seller seller;

    @Column(name = "thumbnail_hd")
    private String thumbnailHd;
    private Date date;
}
