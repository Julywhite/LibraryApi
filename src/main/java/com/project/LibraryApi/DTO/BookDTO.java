package com.project.LibraryApi.DTO;


public class BookDTO {

    private Long id;
    private String title;
    private String autho;
    private String isbn;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutho() {
        return autho;
    }
    public void setAutho(String autho) {
        this.autho = autho;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
