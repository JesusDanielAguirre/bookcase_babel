package com.babel.babelbackend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;


import com.babel.babelbackend.models.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

}
