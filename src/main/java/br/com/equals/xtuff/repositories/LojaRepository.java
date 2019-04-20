package br.com.equals.xtuff.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.equals.xtuff.domain.entities.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja,Integer> {
}
