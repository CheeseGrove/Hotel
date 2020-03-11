package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.rooms.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
interface RoomRepository extends MongoRepository<Room, String> {

}
