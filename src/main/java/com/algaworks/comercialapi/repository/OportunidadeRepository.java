package com.algaworks.comercialapi.repository;

import java.util.Optional;

import com.algaworks.comercialapi.model.Oportunidade;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OportunidadeRepository
 */
public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long> {

    // exemplo de criação de novos métodos de consulta personalizados: findBy<campoA>And<campoB>(Tipo campoA, Tipo campoB)
    Optional<Oportunidade> findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);
    
}
