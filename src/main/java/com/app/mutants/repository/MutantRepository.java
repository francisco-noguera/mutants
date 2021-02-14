package com.app.mutants.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.mutants.model.Mutant;

@Repository
@Transactional
public interface MutantRepository extends JpaRepository<Mutant, String> {

	public List<Mutant> findByDna(String dna);
	public Long countByMutant(boolean isMutant);	
	
}
