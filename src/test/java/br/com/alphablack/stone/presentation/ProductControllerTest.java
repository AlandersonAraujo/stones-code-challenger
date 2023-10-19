package br.com.alphablack.stone.presentation;

import br.com.alphablack.stone.application.cache.CacheService;
import br.com.alphablack.stone.application.product.ProductService;
import br.com.alphablack.stone.application.seller.SellerService;
import br.com.alphablack.stone.core.client.Client;
import br.com.alphablack.stone.core.product.Product;
import br.com.alphablack.stone.core.seller.Seller;
import br.com.alphablack.stone.presentation.dto.ProductDTO;
import br.com.alphablack.stone.presentation.product.ProductController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @Mock
    private ProductService productService;

    @Mock
    private SellerService sellerService;

    @Mock
    private CacheService cacheService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        Seller newSeller = new Seller();
        newSeller.setName("João da Silva");

        Product newProduct = new Product();
        newProduct.setId(1L);
        newProduct.setTitle("Blusa do Imperio");
        newProduct.setPrice(7990);
        newProduct.setZipcode("78993-000");
        newProduct.setDate(new Date(2015, 11, 26));
        newProduct.setThumbnailHd("https://cdn.awsli.com.br/600x450/21/21351/produto/3853007/f66e8c63ab.jpg");
        newProduct.setSeller(newSeller);

        productList.add(newProduct);
        when(productService.getAllProducts()).thenReturn(productList);
        when(cacheService.get("allProducts")).thenReturn(null);

        ResponseEntity<List<ProductDTO>> responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(cacheService, times(1)).put(eq("allProducts"), anyList(), eq(60));
    }

    @Test
    void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle("Camiseta Star Wars Anakin Vs Obi-wan");
        productDTO.setPrice(70);
        productDTO.setZipcode("78993-000");
        productDTO.setSeller("Alanderson Araujo");
        productDTO.setThumbnailHd("https://cdn.awsli.com.br/600x450/21/21351/produto/3853007/f66e8c63ab.jpg");
        productDTO.setDate(new Date());

        Seller newSeller = new Seller();
        newSeller.setName(productDTO.getSeller());

        Product newProduct = new Product();
        newProduct.setId(1L);
        newProduct.setTitle(productDTO.getTitle());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setZipcode(productDTO.getZipcode());
        newProduct.setDate(new Date());
        newProduct.setThumbnailHd(productDTO.getThumbnailHd());
        newProduct.setSeller(newSeller);


        when(productService.saveProduct(any(Product.class))).thenReturn(newProduct);

        ResponseEntity<ProductDTO> responseEntity = productController.createProduct(productDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
