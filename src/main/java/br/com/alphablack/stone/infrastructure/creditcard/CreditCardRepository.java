package br.com.alphablack.stone.infrastructure.creditcard;

import br.com.alphablack.stone.core.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> findByNumber(String id);
}
