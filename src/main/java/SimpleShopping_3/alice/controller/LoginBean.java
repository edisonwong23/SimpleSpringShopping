package SimpleShopping_3.alice.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // Your login/logout logic here
    public String logout() {
        // Implement logout, e.g., invalidate session, etc.
        return "login.xhtml?faces-redirect=true";  // navigate to login page
    }
}

