package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import com.ecutbildning.hotelmanager.rooms.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void changeBooked(String id, boolean booked){
        Rooms room = findById(id);
        room.setBooked(booked);
        roomsRepository.save(room);
    }

    public void deleteById(String id){
        roomsRepository.deleteById(id);
    }

    public void deleteAll(){
        roomsRepository.deleteAll();
    }

    public void addFood(String id, List<String> fruitList){
        System.out.println(fruitList);
        Rooms room = roomsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        System.out.println(fruitList.stream().collect(Collectors.toList()).toString());
        room.getOrderedFood().addAll(fruitList.stream().filter(elem -> !room.getOrderedFood().contains(elem)).collect(Collectors.toList()));
        System.out.println("Success");
        System.out.println(fruitList.toString());
        System.out.println(room.getOrderedFood());
        roomsRepository.save(room);
    }

    public void removeFood(String id, List<String> fruitlist){
        Rooms room = roomsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        room.getOrderedFood().removeAll(room.getOrderedFood().stream().filter(fruitlist::contains).collect(Collectors.toList()));
        roomsRepository.save(room);

    }
}