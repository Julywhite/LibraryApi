package com.project.LibraryApi.Service;

import com.project.LibraryApi.Model.Entity.Book;
import com.project.LibraryApi.Model.Repository.BookRepository;
import com.project.LibraryApi.Service.Impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new BookServiceImpl( repository );
    }

    @Test
    @DisplayName("Deve Salvar instância de livro")
    public void saveBookTest() {

        Book book = Book.builder().author("Fulano").isbn("123").title("As Aventuras").build();

        Mockito.when( repository.save(book) ).thenReturn( Book.builder().id(1L)
                                                                .isbn("123")
                                                                .title("As Aventuras")
                                                                .author("Fulano")
                                                                .build()
                                                            );

        Book savedBook = service.save(book);

        Assertions.assertThat( savedBook.getId() ).isNotNull();
        Assertions.assertThat( savedBook.getIsbn() ).isEqualTo( "123" );
        Assertions.assertThat( savedBook.getTitle() ).isEqualTo( "As Aventuras" );
        Assertions.assertThat( savedBook.getAuthor() ).isEqualTo( "Fulano" );
    }
}
