package com.raphaelagra.propostaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raphaelagra.propostaapp.entity.Proposta;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long>{

	List<Proposta> findAllByIntegradaIsFalse();

	@Transactional
	@Modifying
	@Query(value = "UPDATE proposta SET aprovada = :aprovada, observacao = :observacao WHERE id = :id", nativeQuery = true)
	void atualizarProposta(Long id, boolean aprovada, String observacao);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Proposta SET integrada = :integrada WHERE id = :id")
	void atualizarStatusIntegrada(Long id, boolean integrada);
}
