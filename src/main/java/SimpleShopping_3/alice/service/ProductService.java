package SimpleShopping_3.alice.service;

import SimpleShopping_3.alice.entity.Product;
import SimpleShopping_3.alice.security.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(String name, double price) {
        Product product = new Product();
        product.setName(name);
        product.setEncryptedPrice(EncryptionUtils.encrypt(Double.toString(price)));
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, double price) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        product.setName(name);
        product.setEncryptedPrice(EncryptionUtils.encrypt(Double.toString(price)));
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> listAll() {
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            if (product.getEncryptedPrice() != null) {
                try {
                    String decrypted = EncryptionUtils.decrypt(product.getEncryptedPrice());
                    product.setPrice(Double.parseDouble(decrypted));
                } catch (Exception e) {
                    // Optional: log or handle bad decryption
                    System.out.println("Error: " + e.getMessage());
                    product.setPrice(null);
                }
            }
        }

        return products;
    }

    public double getPrice(Product product) {
        String decrypted = EncryptionUtils.decrypt(product.getEncryptedPrice());
        return Double.parseDouble(decrypted);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}

