package com.babel.babelbackend.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babel.babelbackend.models.Book;
import com.babel.babelbackend.repositorys.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public  List<Book> findBooks() {
		
		return (List<Book>) bookRepository.findAll();
	}

	public void addBook(Book book) {
		
		bookRepository.save(book);
		
	}

	public Book findBook( Long id) {
		try {
		Optional<Book> book =bookRepository.findById(id);
		log.info("book:"+ book.get().toString());
		if(book.isPresent()) {
			
			return book.get();
		}
		throw new NoResultException("Libro no encontrado");
		}
		catch(NoResultException e) {
			e.getMessage();
			throw e;
		}
		
		
	}

	public Book updateBook(Book book) {
		if(book.getId() == null) {
			
			throw new NoResultException("Id no puede estar vacio");
		}
		
		try {
			
			return  bookRepository.save(book); //Guardamos
			
		}catch(NoResultException e) {
			
			e.getMessage();
			throw e;
			
		}
		
		
	}
	
	public Map<String, Boolean> deleteBook( Long id) {
		try {
			
		Optional<Book> book =bookRepository.findById(id);
		log.info("book:"+ book.get().toString());
		Map<String, Boolean> response = new HashMap<>();
		if(book.isPresent()) {
			
			bookRepository.deleteById(id);
			response.put("se pudo eliminar el Libro", Boolean.TRUE);
		    return response;
		}
		throw new NoResultException("Libro no encontrado");
		}
		catch(NoResultException e) {
			e.getMessage();
			throw e;
		}

	}
}
