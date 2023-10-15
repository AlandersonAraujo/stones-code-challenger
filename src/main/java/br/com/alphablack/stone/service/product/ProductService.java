package br.com.alphablack.stone.service.product;

import br.com.alphablack.stone.model.product.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
}
