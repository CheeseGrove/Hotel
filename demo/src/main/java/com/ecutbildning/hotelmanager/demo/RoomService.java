package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import com.ecutbildning.hotelmanager.rooms.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    public RoomRepository roomRepository;
    public CustomerRepository customerRepository;

    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    public Room findById(String id){
        return roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Room create (String type){
        Room room = new Room(type);
        return roomRepository.save(room);
    }

    public Room save(String id, Room room){
        String tempID = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new).getId();
        roomRepository.deleteById(id);
        room.setId(tempID);
        return roomRepository.save(room);
    }


    public Room changeBooked(String id, boolean booked){
        Room room = findById(id);
        room.setBooked(booked);
        return roomRepository.save(room);
    }

    public void deleteById(String id){
        roomRepository.deleteById(id);
    }

    public void deleteAll(){
        roomRepository.deleteAll();
    }

    public void addFood(String id, String fruit){
        Room room = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        room.getOrderedFood().add(fruit);
        room.setChargePerDay(room.getChargePerDay() + (fruit.length() * 5));
        roomRepository.save(room);
    }

    public void removeFood(String id, ArrayList<String> fruitlist){
        Room room = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        room.getOrderedFood().removeAll(room.getOrderedFood().stream()
                .filter(fruitlist::contains)
                .collect(Collectors.toList()));
        roomRepository.save(room);
    }

    public void removeAllFood(String id){
        Room room = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        room.getOrderedFood().removeAll(room.getOrderedFood());
        roomRepository.save(room);
    }

    public void removeBooking(String roomID){
        List<Customer> custlist = customerRepository.findAll();
        Customer customer = custlist.stream().filter(c -> c.getBookedRooms().contains(roomID)).collect(Collectors.toList()).get(0);
        customer.getBookedRooms().remove(roomID);
        customerRepository.save(customer);
    }

    public void setFood(String id, ArrayList<String> fruitlist){
        Room room = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        room.setOrderedFood(fruitlist);

    }

}