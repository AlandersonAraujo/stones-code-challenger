package br.com.alphablack.stone.repository.product;

import br.com.alphablack.stone.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
