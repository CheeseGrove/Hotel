package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.rooms.Rooms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Component;

@Component
interface RoomsRepository extends MongoRepository<Rooms, String> {

}
