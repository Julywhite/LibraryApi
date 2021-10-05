package com.project.LibraryApi.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.LibraryApi.DTO.BookDTO;
import com.project.LibraryApi.Model.Entity.Book;
import com.project.LibraryApi.Service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    static String BOOK_API = "/api/books";

    @MockBean
    BookService service;

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("POST method to create a new register")
    public void createBookTest() throws Exception {

        BookDTO dto = BookDTO.builder().author("Author").title("Meu livro").isbn("1213212").build();

        Book savedBook = Book.builder().id(10L).author("Author").title("Meu livro").isbn("1213212").build();

        BDDMockito.given( service.save( Mockito.any( Book.class ) ) ).willReturn( savedBook );

        String json = new ObjectMapper().writeValueAsString( dto );

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                    .post( BOOK_API )
                                                    .contentType( MediaType.APPLICATION_JSON )
                                                    .accept( MediaType.APPLICATION_JSON )
                                                    .content( json );

        mvc.perform(request)
                .andExpect( status().isCreated() )
                .andExpect( jsonPath( "id" ).isNotEmpty() )
                .andExpect( jsonPath("title").value( dto.getTitle() ) )
                .andExpect( jsonPath( "author" ).value( dto.getAuthor() ) )
                .andExpect( jsonPath( "isbn" ).value( dto.getIsbn() ) );
    }

    @Test
    @DisplayName("Thows a exception when POST method to create a new book fail")
    public void createInvalidBookTest() throws Exception{

        String json = new ObjectMapper().writeValueAsString( new BookDTO() );

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                                                    .post( BOOK_API )
                                                    .contentType( MediaType.APPLICATION_JSON )
                                                    .accept( MediaType.APPLICATION_JSON )
                                                    .content( json );

        mvc.perform(request)
                .andExpect( status().isBadRequest() )
                .andExpect( jsonPath("errors", hasSize(1)) );
    }
}
