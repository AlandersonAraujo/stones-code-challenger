package br.com.alphablack.stone.model.purchase;

import br.com.alphablack.stone.model.client.Client;
import br.com.alphablack.stone.model.creditcard.CreditCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    private Integer purchaseValue;
    private Date purchaseDate;
    @ManyToOne
    private CreditCard creditCard;

}