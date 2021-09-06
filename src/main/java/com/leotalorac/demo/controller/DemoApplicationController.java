package com.leotalorac.demo.controller;

import com.leotalorac.demo.model.Book;
import com.leotalorac.demo.service.DemoApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RestController
public class DemoApplicationController {

    private final DemoApplicationService bookService;
    //    dependency injections
    @Autowired
    public DemoApplicationController(DemoApplicationService bookService){
        this.bookService = bookService;
    }
    //    Simple get request
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }
    //    Get request with params
    @GetMapping("/greet")
    public String greet(@RequestParam(required = false) String language){
        if(language!=null) {
            switch (language) {
                case "es":
                    return "Hola";
                case "en":
                    return "Hello";
                case "de":
                    return "Hallo";
                default:
                    return "No idea";
            }
        }else{
            return "Ash :(";
        }
    }
    //  call a controller
    @GetMapping("/books")
    public List<Book> getallBooks(){
        return  this.bookService.getBooks();
    }

    //    Using optional to null objects
    //    get with more logic
    @GetMapping("/books-genre")
    public List<Book> filterBooks(@RequestParam(required = false) Optional<String> genre){
        return this.bookService.getGenreBooks(genre);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Integer id){
            return this.bookService
                    .getBook(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(ResponseEntity.noContent()::build);
    }

}
