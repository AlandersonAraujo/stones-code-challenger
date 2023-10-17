package br.com.alphablack.stone.application.creditcard;

import br.com.alphablack.stone.core.creditcard.CreditCard;

import java.util.Optional;

public interface CreditCardService {
    CreditCard saveCreditCard(CreditCard creditCard);
    Optional<CreditCard> findByNumber(String number);
}
