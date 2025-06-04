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

    public Product addProduct(String name, String price) {
        Product product = new Product();
        product.setName(name);
        product.setEncryptedPrice(EncryptionUtils.encrypt(price));
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, String price) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        product.setName(name);
        product.setEncryptedPrice(EncryptionUtils.encrypt(price));
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
                    product.setPrice(displayDecryptedFormattedPrice(product.getEncryptedPrice()));
                } catch (Exception e) {
                    // Optional: log or handle bad decryption
                    System.out.println("Error: " + e.getMessage());
                    product.setPrice(null);
                }
            }
        }

        return products;
    }

    public String getPrice(Product product) {
        return displayDecryptedFormattedPrice(product.getEncryptedPrice());
    }

    private String displayDecryptedFormattedPrice(String encryptedPrice) {
        final String decrypted = EncryptionUtils.decrypt(encryptedPrice); //Decrypt the price from DB
        final double priceDouble = Double.parseDouble(decrypted); //Parse it to Double
        return String.format("%.2f", priceDouble);        // format to 2 decimal places
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}

