package com.app.mutants.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.app.mutants.exceptions.MutantGenericException;
import com.app.mutants.pojo.ServiceResponse;

public interface MutantService {

	public ServiceResponse isMutant(List<String> dna) throws MutantGenericException, InterruptedException, ExecutionException;
	public ServiceResponse getStats() throws MutantGenericException;
	
}
