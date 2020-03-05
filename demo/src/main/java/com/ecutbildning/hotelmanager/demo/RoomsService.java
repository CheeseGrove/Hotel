package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import com.ecutbildning.hotelmanager.rooms.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {

    @Autowired
    private RoomsRepository roomsRepository;

    public List<Rooms> findAll(){
        return roomsRepository.findAll();
    }

    public Rooms findById(String id){
        return roomsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Rooms save(Rooms rooms){
        return roomsRepository.save(rooms);
    }

    public void deleteById(String id){
        roomsRepository.deleteById(id);
    }
}
