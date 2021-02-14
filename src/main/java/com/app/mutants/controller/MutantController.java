package com.app.mutants.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.mutants.model.Mutant;
import com.app.mutants.pojo.ServiceResponse;
import com.app.mutants.service.MutantService;
import com.app.mutants.utils.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/mutants")
public class MutantController {

	private static final Logger logger = LoggerFactory.getLogger(MutantController.class);
	
	@Autowired
	private MutantService mutantService;
	
	@Operation(summary = "Validates if the DNA contains Mutant traces")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "The DNA contains Mutant traces", 
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceResponse.class)) }),
            @ApiResponse(responseCode = "60001", description = "Invalid DNA information supplied", content = @Content),
            @ApiResponse(responseCode = "60002", description = "Invalid DNA matrix size", content = @Content),
            @ApiResponse(responseCode = "403", description = "The DNA doesn't contains Mutant traces", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content)})
	@PostMapping("/dna")
	public ResponseEntity<ServiceResponse> validateDNA(@RequestBody List<String> dna) throws JsonProcessingException {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			serviceResponse = mutantService.isMutant(dna);
			if(((Mutant)serviceResponse.getResponseData()).isMutant()) {
				return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(serviceResponse, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			HttpUtils.preparedErrorResponse(
        			serviceResponse, 
        			e.getMessage(), 
        			String.valueOf(INTERNAL_SERVER_ERROR.value())
            	);
            logger.error(".:: End Validate DNA Error Response : {}", HttpUtils.printInformation(serviceResponse), e);
            return new ResponseEntity<>(serviceResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Get stats from mutant and human DNA")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Information with reviewed DNA stats", 
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server error", content = @Content)})
	@GetMapping("/stats")
	public ResponseEntity<ServiceResponse> dnaStats() throws JsonProcessingException {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			serviceResponse = mutantService.getStats();
			return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
		} catch (Exception e) {
			HttpUtils.preparedErrorResponse(
        			serviceResponse, 
        			e.getMessage(), 
        			String.valueOf(INTERNAL_SERVER_ERROR.value())
            	);
            logger.error(".:: End Validate DNA Error Response : {}", HttpUtils.printInformation(serviceResponse), e);
            return new ResponseEntity<>(serviceResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
