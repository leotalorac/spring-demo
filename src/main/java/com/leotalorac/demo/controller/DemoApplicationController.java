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
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("OK");
    }
    //    Get request with params
    @GetMapping("/greet")
    public ResponseEntity<String> greet(@RequestParam(required = false) String language){
        String greet = "No idea";
        if(language!=null) {
            switch (language) {
                case "es":
                     greet ="Hola";
                     break;
                case "en":
                    greet="Hello";
                    break;
                case "de":
                    greet= "Hallo";
                    break;
            }
            return ResponseEntity.ok(greet);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    //  call a controller
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getallBooks(){
        return  ResponseEntity.ok(this.bookService.getBooks());
    }

    //    Using optional to null objects
    //    get with more logic
    @GetMapping("/books-genre")
    public ResponseEntity<List<Book>> filterBooks(@RequestParam(required = false) Optional<String> genre){
        return ResponseEntity.ok(this.bookService.getGenreBooks(genre));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Integer id){
            return this.bookService
                    .getBook(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(ResponseEntity.noContent()::build);
    }

}
