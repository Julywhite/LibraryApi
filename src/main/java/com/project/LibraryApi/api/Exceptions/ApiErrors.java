package com.project.LibraryApi.api.Exceptions;

import org.springframework.validation.BindingResult;
import java.util.ArrayList;
import java.util.List;


public class ApiErrors {

    private List<String> errors;


    public ApiErrors( BindingResult bindingResult ) {

        this.errors = new ArrayList<>();

        bindingResult.getAllErrors().forEach( er -> this.errors.add( er.getDefaultMessage() ) );
    }

    public List<String> getErrors() {
        return errors;
    }
}
