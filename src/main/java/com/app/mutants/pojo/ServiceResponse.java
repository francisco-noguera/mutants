package com.app.mutants.pojo;

import java.io.Serializable;
import java.util.List;

public class ServiceResponse implements Serializable {
	
	private static final long serialVersionUID = 6645019613121063878L;
	private List<? extends ServiceError> errors;
    private int numberOfErrors;
    private Object responseData;

    public ServiceResponse(Object responseData) {
        this.responseData = responseData;
        this.numberOfErrors = 0;
    }

    public ServiceResponse(List<ServiceError> errors) {
        this.errors = errors;
        this.numberOfErrors = errors.size();
    }

    public ServiceResponse(){}

    public List<? extends ServiceError> getErrors() {
        return errors;
    }

    public void setErrors(List<? extends ServiceError> errors) {
        this.errors = errors;
        this.numberOfErrors = errors.size();
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
        this.numberOfErrors = 0;
    }

    public int getNumberOfErrors() {
        return numberOfErrors;
    }

    public void setNumberOfErrors(int numberOfErrors) {
        this.numberOfErrors = numberOfErrors;
    }
}
