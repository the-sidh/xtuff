package br.com.equals.xtuff.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import br.com.equals.xtuff.domain.entities.Comerciante;

@Repository
public interface ComercianteRepository  extends JpaRepository<Comerciante, Integer> {
    List<Comerciante> findByEmail( String email);

}
