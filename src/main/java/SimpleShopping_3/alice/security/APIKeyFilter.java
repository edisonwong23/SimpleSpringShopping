package SimpleShopping_3.alice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class APIKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY = "MySecureApiKey_123"; // My Api Key
    private static final String HEADER_NAME = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path.startsWith("/api/")) {
            String authHeader = request.getHeader(HEADER_NAME);

            if (authHeader == null || !authHeader.equals(API_KEY)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unauthorized - invalid or missing API key");
                return;
            } else {
                // Create an authenticated token with ROLE_API_USER (or any role you want)
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        "apiKeyUser",  // principal (can be any string since no real user)
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_API_USER")));  // roles/authorities
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(request, response); // allow request if authorized or not /api/
    }

}
