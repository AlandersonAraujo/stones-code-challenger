package br.com.alphablack.stone.presentation.product;

import br.com.alphablack.stone.application.cache.CacheService;
import br.com.alphablack.stone.application.seller.SellerService;
import br.com.alphablack.stone.core.seller.Seller;
import br.com.alphablack.stone.presentation.dto.ProductDTO;
import br.com.alphablack.stone.core.product.Product;
import br.com.alphablack.stone.application.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/starstore/product")
public class ProductController {

    private final ProductService productService;
    private final SellerService sellerService;
    private final CacheService cacheService;

    @Autowired
    public ProductController(ProductService productService, SellerService sellerService, CacheService cacheService) {
        this.productService = productService;
        this.sellerService = sellerService;
        this.cacheService = cacheService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> cachedProducts = (List<ProductDTO>) cacheService.get("allProducts");

        if (cachedProducts != null) {
            return ResponseEntity.ok(cachedProducts);
        }

        List<Product> productList = productService.getAllProducts();

        List<ProductDTO> productDTOList = productList.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());

        cacheService.put("allProducts", productDTOList, 60);

        return ResponseEntity.ok(productDTOList);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Product newProduct = this.convertToProduct(productDTO);
        Product savedProduct = productService.saveProduct(newProduct);
        ProductDTO savedProductDTO = convertToProductDTO(savedProduct);

        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    private Product convertToProduct(ProductDTO productDTO) {
        Seller seller = getOrCreateSeller(productDTO.getSeller());

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setZipcode(productDTO.getZipcode());
        product.setThumbnailHd(productDTO.getThumbnailHd());

        product.setSeller(seller);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = dateFormat.parse(productDTO.getDate());
            product.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return product;
    }

    private Seller getOrCreateSeller(String name) {
        Optional<Seller> existingSeller = sellerService.findByName(name);

        if (existingSeller.isPresent()) {
            return existingSeller.get();
        }

        Seller newSeller = new Seller();
        newSeller.setName(name);
        return sellerService.saveSeller(newSeller);
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
