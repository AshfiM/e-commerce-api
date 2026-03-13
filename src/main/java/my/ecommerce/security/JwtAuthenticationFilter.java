package my.ecommerce.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String getJwtFromCookie(Cookie[] cookies) {
        if (cookies == null) return "";
        for (Cookie c :cookies) {
            if ("jwt-token".equals(c.getName())) {
                    return c.getValue();
            }
        }
        return "";
    }
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
    throws ServletException, IOException {

        String token = getJwtFromCookie(req.getCookies());
        if(!token.equals("") && jwtService.validateToken(token)) {
            String userName = jwtService.extractUsername(token);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userName, null, new ArrayList<>()
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        filterChain.doFilter(req, resp);
    }
}
