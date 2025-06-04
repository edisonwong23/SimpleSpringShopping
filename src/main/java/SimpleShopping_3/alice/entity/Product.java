package SimpleShopping_3.alice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // Store encrypted price as a Base64 string
    @Column(name = "price", nullable = false)
    private String encryptedPrice;

    // Transient property for UI usage (not persisted)
    @Transient
    private String price;

    public Product() {
    }

    public Product(String name, String encryptedPrice) {
        this.name = name;
        this.encryptedPrice = encryptedPrice;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncryptedPrice() {
        return encryptedPrice;
    }

    public void setEncryptedPrice(String encryptedPrice) {
        this.encryptedPrice = encryptedPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", encryptedPrice='" + encryptedPrice + '\'' +
                ", price=" + price +
                '}';
    }
}

