package br.com.alphablack.stone.application.product;

import br.com.alphablack.stone.core.product.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
}
