package br.com.alphablack.stone.infrastructure.purchase;

import br.com.alphablack.stone.core.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAllByClient_Id(Long id);
}
