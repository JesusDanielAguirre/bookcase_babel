package com.babel.babelbackend.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.babel.babelbackend.models.Book;



@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	Optional<Book> findById(Long id);
}
