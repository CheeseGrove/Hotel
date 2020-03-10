package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import com.ecutbildning.hotelmanager.rooms.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomsService {

    @Autowired
    public RoomsRepository roomsRepository;

    public List<Rooms> findAll(){
        return roomsRepository.findAll();
    }

    public Rooms findById(String id){
        return roomsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Rooms create (String type){
        Rooms room = new Rooms(type);
        return roomsRepository.save(room);
    }

    public Rooms save(Rooms rooms){
        return roomsRepository.save(rooms);
    }

    public Rooms changeBooked(String id, boolean booked){
        Rooms room = findById(id);
        room.setBooked(booked);
        deleteById(id);
        System.out.println();
        return roomsRepository.save(room);
    }

    public void deleteById(String id){
        roomsRepository.deleteById(id);
    }

    public void deleteAll(List<Rooms> roomsList){
        roomsRepository.deleteAll(roomsList);
    }
}