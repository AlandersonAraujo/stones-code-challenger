package br.com.alphablack.stone.presentation.product;

import br.com.alphablack.stone.adapters.cache.CacheGateway;
import br.com.alphablack.stone.presentation.dto.ProductDTO;
import br.com.alphablack.stone.core.product.Product;
import br.com.alphablack.stone.application.product.ProductService;
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
    private final CacheGateway cacheGateway;

    @Autowired
    public ProductController(ProductService productService, CacheGateway cacheGateway) {
        this.productService = productService;
        this.cacheGateway = cacheGateway;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> cachedProducts = (List<ProductDTO>) cacheGateway.get("allProducts");

        if (cachedProducts != null) {
            System.out.println("Tem cache");
            return ResponseEntity.ok(cachedProducts);
        }

        List<Product> productList = productService.getAllProducts();

        List<ProductDTO> productDTOList = productList.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());

        cacheGateway.put("allProducts", productDTOList, 60);

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
