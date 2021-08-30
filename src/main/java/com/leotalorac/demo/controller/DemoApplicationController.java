package com.leotalorac.demo.controller;

import com.leotalorac.demo.model.Book;
import com.leotalorac.demo.service.DemoApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DemoApplicationController {
//    dependency injections
    @Autowired
    private DemoApplicationService bookService;

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


}
