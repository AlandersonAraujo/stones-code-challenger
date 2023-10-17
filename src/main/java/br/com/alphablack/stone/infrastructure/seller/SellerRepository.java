package br.com.alphablack.stone.infrastructure.seller;

import br.com.alphablack.stone.core.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByName(String name);
}
