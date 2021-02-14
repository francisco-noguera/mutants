package com.app.mutants.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static com.app.mutants.utils.ErrorCodes.INVALID_DNA_CHAIN_SIZE;
import static com.app.mutants.utils.ErrorCodes.INVALID_DNA_MATRIX_SIZE;
import static com.app.mutants.utils.ErrorCodes.INVALID_DNA_VALUE;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.mutants.exceptions.MutantGenericException;
import com.app.mutants.model.Mutant;
import com.app.mutants.pojo.MutantStatsResponse;
import com.app.mutants.pojo.ServiceResponse;
import com.app.mutants.repository.MutantRepository;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

	@Mock
	private MutantRepository mutantRepository;
	@InjectMocks
	private MutantServiceImpl mutantService;
	
	@Test
	public void dnaRowValidationMutant() throws MutantGenericException, InterruptedException, ExecutionException {
		String[] dnaArray = {"TTGCGA","CAGTAC","TTATGT","AGAAGG","TTCCCC","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(mutant.isMutant());
	}
	
	@Test
	public void dnaColumnValidationMutant() throws MutantGenericException, InterruptedException, ExecutionException {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCATA","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(mutant.isMutant());
	}
	
	@Test
	public void dnaRightDiagonalValidationMutant() throws MutantGenericException, InterruptedException, ExecutionException {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAAG","CCCATA","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(mutant.isMutant());
	}
	
	@Test
	public void dnaLeftDiagonalValidationMutant() throws MutantGenericException, InterruptedException, ExecutionException {
		String[] dnaArray = {"ATGCGA", "CAGTGC", "TTGCGT", "AGCGAG", "CCCATA", "CCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(mutant.isMutant());
	}
	
	@Test
	public void dnaNonMutant() throws MutantGenericException, InterruptedException, ExecutionException {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTGTGT","AGAAAG","CCCATA","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		ServiceResponse response = mutantService.isMutant(dna);
		Mutant mutant = (Mutant) response.getResponseData();
		assertTrue(!mutant.isMutant());
	}
	
	@Test
	public void dnaInvalidMatrixSizeMutant() throws InterruptedException, ExecutionException {
		String[] dnaArray = {"AT","CA"};
		List<String> dna = Arrays.asList(dnaArray);
		try {
			mutantService.isMutant(dna);
		} catch (MutantGenericException e) {
			assertEquals(e.getErrorCode(), INVALID_DNA_MATRIX_SIZE);
		}
	}
	
	@Test
	public void dnaInvalidChainSizeMutant() throws InterruptedException, ExecutionException {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAG","TTCCCC","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		try {
			mutantService.isMutant(dna);
		} catch (MutantGenericException e) {
			assertEquals(e.getErrorCode(), INVALID_DNA_CHAIN_SIZE);
		}
	}
	
	@Test
	public void dnaInvalidChainValueMutant() throws InterruptedException, ExecutionException {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAGC","TTCOCC","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		try {
			mutantService.isMutant(dna);
		} catch (MutantGenericException e) {
			assertEquals(e.getErrorCode(), INVALID_DNA_VALUE);
		}
	}
	
	@Test
	public void getStats() {
		when(mutantRepository.countByMutant(true)).thenReturn(5L);
		when(mutantRepository.countByMutant(false)).thenReturn(1L);
		ServiceResponse response = mutantService.getStats();
		MutantStatsResponse mutantResponse = (MutantStatsResponse) response.getResponseData();
		assertEquals(mutantResponse.getCountMutantDna(), 5L);
		assertEquals(mutantResponse.getCountHumanDna(), 1L);
	}
	
}
