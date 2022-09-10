package com.babel.babelbackend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babel.babelbackend.models.Book;
import com.babel.babelbackend.services.BookService;

@CrossOrigin
@RestController
@RequestMapping(value = "/")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> Books() {
		return bookService.findBooks();
	}

	@GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book findBook(@PathVariable Long id) {
		return bookService.findBook(id);
	}

	@PostMapping(value = "/addbook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addBook(@RequestBody Book book) {
		bookService.addBook(book);
	}
	
	@PutMapping(value = "/updatebook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}
	@DeleteMapping(value = "/deletebook/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Boolean> deleteBook(@PathVariable Long id) {
		return bookService.deleteBook(id);
	}

}
