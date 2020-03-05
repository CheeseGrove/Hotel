package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.rooms.Rooms;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomsRepository extends MongoRepository<Rooms, String> {
}
