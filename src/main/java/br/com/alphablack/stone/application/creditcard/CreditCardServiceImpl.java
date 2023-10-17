package br.com.alphablack.stone.application.creditcard;

import br.com.alphablack.stone.core.creditcard.CreditCard;
import br.com.alphablack.stone.infrastructure.creditcard.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return this.creditCardRepository.save(creditCard);
    }

    @Override
    public Optional<CreditCard> findByNumber(String number) {
        return this.creditCardRepository.findByNumber(number);
    }
}
