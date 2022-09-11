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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name ="location")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_location")
	private Long id;
	
	@NonNull
	@Column(name = "hallway")
	private Integer hallway;
	
	@NonNull
	@Column(name = "bookcase")
	private Integer bookcase;
	
	@NonNull
	@Column(name = "position")
	private Integer position;
	
	@NonNull
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_catalog_id_room",foreignKey =@ForeignKey(name = "fk_location_room_catalog1"))
    private Room room;
}
