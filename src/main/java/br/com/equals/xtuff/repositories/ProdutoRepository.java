package br.com.equals.xtuff.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.equals.xtuff.domain.entities.Produto;



@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}

