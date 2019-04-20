package br.com.equals.xtuff.auth.spring;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import static br.com.equals.xtuff.auth.spring.config.TokenConfig.HEADER_STRING;
import static br.com.equals.xtuff.auth.spring.config.TokenConfig.SECRET;
import static br.com.equals.xtuff.auth.spring.config.TokenConfig.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {

            // parse the token.

            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build(); // Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token.replace(TOKEN_PREFIX, ""));
            String comerciante = jwt.getSubject();
           // String role = jwt.getClaims().get("roles").asList(String.class).get(0);
           // request.setAttribute("role", role);

            if (comerciante != null) {
                return new UsernamePasswordAuthenticationToken(comerciante, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}