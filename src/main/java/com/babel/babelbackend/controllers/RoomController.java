package com.babel.babelbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babel.babelbackend.models.Book;
import com.babel.babelbackend.models.Room;
import com.babel.babelbackend.services.BookService;
import com.babel.babelbackend.services.RoomService;

@CrossOrigin
@RestController
@RequestMapping(value = "/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping(value = "/catalog", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Room> rooms() {
		return roomService.findRooms();
	}
	
	
	

}
