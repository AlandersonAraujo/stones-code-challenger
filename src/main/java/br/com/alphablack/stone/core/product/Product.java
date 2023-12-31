package br.com.alphablack.stone.core.product;

import br.com.alphablack.stone.core.seller.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Getter
@Setter
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
