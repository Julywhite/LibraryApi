package com.project.LibraryApi.Controller;

import com.project.LibraryApi.DTO.BookDTO;
import com.project.LibraryApi.Model.Entity.Book;
import com.project.LibraryApi.Service.BookService;
import com.project.LibraryApi.api.Exceptions.ApiErrors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;
    private ModelMapper modelMapper;

    public BookController(BookService service, ModelMapper mapper) {
        this.service = service;
        this.modelMapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create( @RequestBody @Valid BookDTO dto ) {
        Book entity = modelMapper.map( dto, Book.class);

        entity = service.save( entity );

        return modelMapper.map( entity, BookDTO.class );
    }

    @ExceptionHandler( IllegalArgumentException.class )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public String handleValidationExceptions( IllegalArgumentException ex ) {

        String message = ex.getMessage();

        return new String( message );
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ApiErrors handleValidationExceptions( MethodArgumentNotValidException ex ) {

        BindingResult bindingResult = ex.getBindingResult();

        return new ApiErrors( bindingResult );
    }
}
