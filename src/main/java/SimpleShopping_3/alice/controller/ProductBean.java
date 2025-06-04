package SimpleShopping_3.alice.controller;

import SimpleShopping_3.alice.entity.Product;
import SimpleShopping_3.alice.service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductBean implements Serializable {

    @Autowired
    private ProductService productService;

    private List<Product> products;
    private Product product = new Product();

    // init products list from service
    @PostConstruct
    public void init() {
        products = productService.listAll();
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Save or update
    public String save() {

        if (product.getId() == null) {
            Product p = productService.addProduct(product.getName(), product.getPrice());
            products.add(p);
        } else {
            Product p = productService.updateProduct(product.getId(), product.getName(), product.getPrice());
            // update the local list
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(p.getId())) {
                    products.set(i, p);
                    break;
                }
            }
        }
        product = new Product();
        return "home.xhtml?faces-redirect=true"; // Trigger a redirect
    }

    public String edit(Product p) {
        // create a copy of product to edit
        this.product = new Product();
        product.setId(p.getId());
        product.setName(p.getName());
        product.setPrice(productService.getPrice(p));  // decrypt price for UI
        return null; // Trigger a redirect
    }

    public String delete(Product p) {
        productService.deleteProduct(p.getId());
        products.removeIf(prod -> prod.getId().equals(p.getId()));
        return "home.xhtml?faces-redirect=true"; // Trigger a redirect
    }
}
