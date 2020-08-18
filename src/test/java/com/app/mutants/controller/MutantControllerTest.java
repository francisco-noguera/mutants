package com.app.mutants.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.app.mutants.utils.ErrorCodes.INVALID_DNA_MATRIX_SIZE;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.mutants.exceptions.MutantGenericException;
import com.app.mutants.model.Mutant;
import com.app.mutants.pojo.ServiceResponse;
import com.app.mutants.service.MutantService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class MutantControllerTest {
	
	@Mock
	private MutantService mutantService;
	@InjectMocks
	private MutantController mutantController;
	
	
	@Test
	public void dnaValidationMutant() throws JsonProcessingException {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","TTCCCC","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		JSONArray jsonArray = new JSONArray(dna);
		Mutant mutant = new Mutant(UUID.randomUUID().toString(), jsonArray.toString(), true);
		ServiceResponse response = new ServiceResponse(mutant);
		when(mutantService.isMutant(dna)).thenReturn(response);
		ResponseEntity<ServiceResponse> responseEntity = mutantController.validateDNA(dna);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK); 
	}
	
	@Test
	public void dnaValidationNonMutant() throws JsonProcessingException {
		String[] dnaArray = {"ATGCGA","CAGTGC","TTGTGT","AGAAAG","CCCATA","TCACTG"};
		List<String> dna = Arrays.asList(dnaArray);
		JSONArray jsonArray = new JSONArray(dna);
		Mutant mutant = new Mutant(UUID.randomUUID().toString(), jsonArray.toString(), false);
		ServiceResponse response = new ServiceResponse(mutant);
		when(mutantService.isMutant(dna)).thenReturn(response);
		ResponseEntity<ServiceResponse> responseEntity = mutantController.validateDNA(dna);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.FORBIDDEN);
	}
	
	@Test
	public void dnaValidationError() throws JsonProcessingException {
		String[] dnaArray = {"AT","CA"};
		List<String> dna = Arrays.asList(dnaArray);
		when(mutantService.isMutant(dna)).thenThrow(new MutantGenericException(INVALID_DNA_MATRIX_SIZE, UUID.randomUUID().toString()));
		ResponseEntity<ServiceResponse> responseEntity = mutantController.validateDNA(dna);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
