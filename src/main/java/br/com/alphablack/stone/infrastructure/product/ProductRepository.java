package br.com.alphablack.stone.infrastructure.product;

import br.com.alphablack.stone.core.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
