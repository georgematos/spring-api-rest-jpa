package com.algaworks.comercialapi.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import com.algaworks.comercialapi.model.Oportunidade;
import com.algaworks.comercialapi.repository.OportunidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OportunidadeService
 */
@Service
public class OportunidadeService {

    private OportunidadeRepository repository;

    @Autowired
    public OportunidadeService(OportunidadeRepository repository) {
        this.repository = repository;
    }

    public List<Oportunidade> findAll() {
        return repository.findAll();
    }

    public Optional<Oportunidade> findById(Long id) {
        return repository.findById(id);
    }

    public Oportunidade save(Oportunidade entity) {
        Optional<Oportunidade> oportunidadeExistente = repository.findByDescricaoAndNomeProspecto(entity.getDescricao(), entity.getNomeProspecto());

        if (oportunidadeExistente.isPresent()) {
            throw new EntityExistsException();
        }
        
        return repository.save(entity);
    }

    public Oportunidade uptade(Long id, Oportunidade entity) {

        Oportunidade oportunidade = repository.getOne(id);

        fillEntityToUpdate(entity, oportunidade);

        Oportunidade oportunidadeAtualizada = repository.save(oportunidade);

        return oportunidadeAtualizada;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void fillEntityToUpdate(Oportunidade entity, Oportunidade oportunidade) {
        oportunidade.setDescricao(entity.getDescricao());
        oportunidade.setNomeProspecto(entity.getNomeProspecto());
        oportunidade.setValor(entity.getValor());
    }

}
