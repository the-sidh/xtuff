package br.com.equals.xtuff.auth.spring;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static br.com.equals.xtuff.auth.spring.config.TokenConfig.EXPIRATION_TIME;
import static br.com.equals.xtuff.auth.spring.config.TokenConfig.HEADER_STRING;
import static br.com.equals.xtuff.auth.spring.config.TokenConfig.SECRET;
import static br.com.equals.xtuff.auth.spring.config.TokenConfig.TOKEN_PREFIX;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;



import br.com.equals.xtuff.domain.entities.Comerciante;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Comerciante comerciante = new ObjectMapper()
                    .readValue(req.getInputStream(), Comerciante.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            comerciante.getEmail(),
                            comerciante.getSenha(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

}
