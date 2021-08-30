package com.leotalorac.demo.service;

import com.leotalorac.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DemoApplicationService {
    private List<Book> books;

    public DemoApplicationService(){
        books = new ArrayList<>();
        this.createMockBook();
    }
    private void createMockBook(){
        String[] genre = {"horror","comedy","drama"};
        int index =0;
        IntStream.range(0,100).forEach(i->{
            Book book = new Book(i,"Book name " + i,genre[i%genre.length]);
            books.add(book);
        });
    }

    public List<Book> getBooks() {
        return books;
    }
    public List<Book> getGenreBooks(Optional<String> genre){
        return this.books.stream().filter(book-> !genre.isPresent() || book.getGenre().equals(genre.get())).collect(Collectors.toList());
    }
}
