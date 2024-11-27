package uz.jahonservice.loyiha.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.jahonservice.loyiha.entity.User;
import uz.jahonservice.loyiha.jwtService.JwtService;
import uz.jahonservice.loyiha.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyJwtFilter implements Filter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                String username = jwtService.extractUsername(token);
                Optional<User> byUsername = userRepository.findByUsername(username);
                if (byUsername.isPresent()) {
                    User user = byUsername.get();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            null
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (Exception ignored){
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
