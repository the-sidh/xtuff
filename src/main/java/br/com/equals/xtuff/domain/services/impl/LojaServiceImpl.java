package br.com.equals.xtuff.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import br.com.equals.xtuff.domain.entities.Endereco;
import br.com.equals.xtuff.domain.entities.Loja;
import br.com.equals.xtuff.domain.entities.Produto;
import br.com.equals.xtuff.domain.exceptions.EntityNotFoundException;
import br.com.equals.xtuff.domain.services.LojaService;
import br.com.equals.xtuff.domain.services.ProdutoService;
import br.com.equals.xtuff.repositories.LojaRepository;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    LojaRepository lojaRepository;

    @Autowired
    ProdutoService produtoService;

    @Override
    public void updateLoja(Loja loja) {
        lojaRepository.save(loja);
    }

    @Override
    public Loja addLoja(Loja loja) {
        Loja persisted = lojaRepository.save(loja);
        return persisted;
    }

    @Override
    public Loja findLoja(int id) throws EntityNotFoundException {
        Optional<Loja> loja = lojaRepository.findById(id);

        if (!loja.isPresent())
            throw new EntityNotFoundException("id-" + id);
        return loja.get();
    }

    @Override
    public Loja persistLoja(Loja loja) {
        Loja persistedLoja = null;
        try {
            persistedLoja = lojaRepository.save(loja);
        } catch (Exception e) {

        }
        return persistedLoja;
    }
}
