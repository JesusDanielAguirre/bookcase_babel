package com.babel.babelbackend.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name ="book")
public class Book {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_book")
	private Long id;
	
	@Column(name = "book_title")
	private String bookTitle;
	
	@Column(name = "book_volume")
	private String bookVolume;
	
	@NonNull
	 @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id_location",foreignKey =@ForeignKey(name = "fk_book_location"))
    private Location location;
	// @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "location_id_location")
//    private Location location;
//	
	

}
