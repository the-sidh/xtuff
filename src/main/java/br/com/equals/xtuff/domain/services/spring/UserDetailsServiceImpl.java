package br.com.equals.xtuff.domain.services.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import br.com.equals.xtuff.dao.ComercianteDao;
import br.com.equals.xtuff.domain.entities.Comerciante;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ComercianteDao comercianteDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Comerciante comerciante = comercianteDao.findByUsername(userName);
        if (comerciante == null) throw new UsernameNotFoundException(userName);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();



        return new org.springframework.security.core.userdetails.User(comerciante.getEmail(), comerciante.getSenha(), grantedAuthorities);
    }
}
