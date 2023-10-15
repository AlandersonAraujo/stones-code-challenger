package br.com.alphablack.stone.model.transaction;

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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    private Integer value;
    private Date date;
    @ManyToOne
    private CreditCard creditCard;

}
