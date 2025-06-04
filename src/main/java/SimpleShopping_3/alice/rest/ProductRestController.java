package SimpleShopping_3.alice.rest;

import SimpleShopping_3.alice.entity.Product;
import SimpleShopping_3.alice.exception.ResourceNotFoundException;
import SimpleShopping_3.alice.service.ProductService;
import jakarta.faces.annotation.RequestMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    // DTO class to control JSON output structure
    public static class ProductDto {
        private String name;
        private String price;

        public ProductDto(String name, String price) {
            this.name = name;
            this.price = price;
        }

        // getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getPrice() { return price; }
        public void setPrice(String price) { this.price = price; }
    }

    // GET all products as JSON
    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.listAll();

        // Convert entities to DTO with decrypted price as String
        return products.stream()
                .map(p -> new ProductDto(
                        p.getName(),
                        String.valueOf(productService.getPrice(p)) // price as string
                ))
                .collect(Collectors.toList());
    }

    // GET single product by id
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product p = productService.findById(id);
        if (p == null) {
            throw new ResourceNotFoundException("Product not found");
        }
        return new ProductDto(p.getName(), String.valueOf(productService.getPrice(p)));
    }
}
