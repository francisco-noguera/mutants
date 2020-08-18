package com.app.mutants.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.app.mutants.utils.ErrorCodes.INVALID_DNA_CHAIN_SIZE;
import static com.app.mutants.utils.ErrorCodes.INVALID_DNA_MATRIX_SIZE;
import static com.app.mutants.utils.ErrorCodes.INVALID_DNA_VALUE;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.mutants.exceptions.MutantGenericException;
import com.app.mutants.model.Mutant;
import com.app.mutants.pojo.ServiceResponse;
import com.app.mutants.repository.MutantRepository;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

	@Mock
	private MutantRepository mutantRepository;
	@InjectMocks
	private MutantServiceImpl mutantService;
	
	@Test
	public void dnaRowValidationMutant() {
		String[] dnaArray = {"TTGCGA","CAGTAC","TTATGT","AGAAGG","TTCCCC","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(mutant.isMutant());
	}
	
	@Test
	public void dnaColumnValidationMutant() {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCATA","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(mutant.isMutant());
	}
	
	@Test
	public void dnaRightDiagonalValidationMutant() {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAAG","CCCATA","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(mutant.isMutant());
	}
	
	@Test
	public void dnaLeftDiagonalValidationMutant() {
		String[] dnaArray = {"ATGCGA", "CAGTGC", "TTGCGT", "AGCGAG", "CCCATA", "CCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(mutant.isMutant());
	}
	
	@Test
	public void dnaNonMutant() {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTGTGT","AGAAAG","CCCATA","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(!mutant.isMutant());
	}
	
	@Test
	public void dnaInvalidMatrixSizeMutant() {
		String[] dnaArray = {"AT","CA"};
		List<String> dna = Arrays.asList(dnaArray);
		try {
			mutantService.isMutant(dna);
		} catch (MutantGenericException e) {
			assertEquals(e.getErrorCode(), INVALID_DNA_MATRIX_SIZE);
		}
	}
	
	@Test
	public void dnaInvalidChainSizeMutant() {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAG","TTCCCC","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		try {
			mutantService.isMutant(dna);
		} catch (MutantGenericException e) {
			assertEquals(e.getErrorCode(), INVALID_DNA_CHAIN_SIZE);
		}
	}
	
	@Test
	public void dnaInvalidChainValueMutant() {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAGC","TTCOCC","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		try {
			mutantService.isMutant(dna);
		} catch (MutantGenericException e) {
			assertEquals(e.getErrorCode(), INVALID_DNA_VALUE);
		}
	}
	
}
