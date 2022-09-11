package com.babel.babelbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.babel.babelbackend.models.Room;
import com.babel.babelbackend.repositorys.RoomRepository;

@Service
public class RoomService {
	@Autowired
	private RoomRepository roomRepository;
	
public  List<Room> findRooms() {
		
		return (List<Room>) roomRepository.findAll();
	}
}
