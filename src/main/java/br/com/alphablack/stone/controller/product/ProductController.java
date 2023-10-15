package br.com.alphablack.stone.controller.product;

import br.com.alphablack.stone.controller.dto.product.ProductDTO;
import br.com.alphablack.stone.model.product.Product;
import br.com.alphablack.stone.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/starstore/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();

        List<ProductDTO> productDTOList = productList.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDTOList);
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setZipcode(product.getZipcode());
        productDTO.setSeller(product.getSeller().getName());
        productDTO.setThumbnailHd(product.getThumbnailHd());
        productDTO.setDate(product.getDate());

        return productDTO;
    }
}
