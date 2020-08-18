package com.app.mutants.utils;

import static com.app.mutants.utils.MutantConstants.EMPTY;

import java.util.ArrayList;
import java.util.List;

import com.app.mutants.pojo.ServiceError;
import com.app.mutants.pojo.ServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HttpUtils {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public static void preparedErrorResponse(ServiceResponse serviceResponse, String message, String errorCode){
		List<ServiceError> errors = new ArrayList<>();
		ServiceError serviceError = new ServiceError(
				message,
				errorCode);
		errors.add(serviceError);
		serviceResponse.setErrors(errors);
	}
	
    public static String printInformation(Object object) throws JsonProcessingException {

    	if(object == null) {
			return EMPTY;
		}
		return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

}
