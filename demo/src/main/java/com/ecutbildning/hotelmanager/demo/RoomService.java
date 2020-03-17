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

    @Autowired
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
        if(!booked){
            room.setDaysBooked(0);
            room.getOrderedFood().clear();
            room.setFruitCharge(updateFruitCharge(room));
            room.updateTotalCost();
        }
        return roomRepository.save(room);
    }

    public void deleteById(String id) {
        Room room = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (room.getBooked()){
            Customer customer = customerRepository.findAll().stream().filter(c -> c.getBookedRooms().contains(id)).findFirst().get();
            customer.getBookedRooms().remove(id);
            customer.setBillToPay(updateCustomerCost(customer));
         customerRepository.save(customer);
        }
        roomRepository.deleteById(id);
    }

    public void deleteAll(){
        roomRepository.deleteAll();
    }

    public void addFood(String roomID, String fruit){
        Room room = roomRepository.findById(roomID).orElseThrow(EntityNotFoundException::new);
        room.getOrderedFood().add(fruit);
        room.setFruitCharge(updateFruitCharge(room));
        room.setTotalCost(updateRoomCost(room));
        roomRepository.save(room);
        Customer customer = customerRepository.findAll().stream().filter(c -> c.getBookedRooms().contains(roomID)).findFirst().orElseThrow(EntityNotFoundException::new);
        customer.setBillToPay(updateCustomerCost(customer));
        customerRepository.save(customer);
    }

    public void removeFood(String roomID, String fruit){
        Room room = roomRepository.findById(roomID).orElseThrow(EntityNotFoundException::new);
        room.getOrderedFood().remove(fruit);
        room.setFruitCharge(updateFruitCharge(room));
        room.setTotalCost(updateRoomCost(room));
        roomRepository.save(room);
        Customer customer = customerRepository.findAll().stream().filter(c -> c.getBookedRooms().contains(roomID)).findFirst().orElseThrow(EntityNotFoundException::new);
        customer.setBillToPay(updateCustomerCost(customer));
        customerRepository.save(customer);
    }

    public void removeAllFood(String id){
        Room room = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        room.getOrderedFood().removeAll(room.getOrderedFood());
        room.setFruitCharge(0);
        room.setTotalCost(updateRoomCost(room));
        roomRepository.save(room);
        Customer customer = customerRepository.findAll().stream().filter(c -> c.getBookedRooms().contains(room.getId())).findFirst().orElseThrow(EntityNotFoundException::new);
        customer.setBillToPay(updateCustomerCost(customer));
        customerRepository.save(customer);
    }

    public void removeBooking(String roomID){
        Customer customer = customerRepository.findAll().stream().filter(c -> c.getBookedRooms().contains(roomID)).findFirst().orElseThrow(EntityNotFoundException::new);
        Room room = roomRepository.findById(roomID).orElseThrow(EntityNotFoundException::new);
        room.setDaysBooked(0);
        room.setFruitCharge(0);
        room.setTotalCost(0);
        roomRepository.save(room);
        customer.getBookedRooms().remove(roomID);
        customer.setBillToPay(updateCustomerCost(customer));
        customerRepository.save(customer);
    }

    public void setFood(String id, ArrayList<String> fruitlist){
        Room room = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        room.setOrderedFood(fruitlist);

    }

    public int updateFruitCharge(Room room){
        return room.getOrderedFood().stream().map(fruit -> fruit.length() * 7).reduce(0, Integer::sum);
    }

    public int updateRoomCost(Room room){
        return (room.getDaysBooked() * room.getChargePerDay()) + room.getFruitCharge();
    }

    public int updateCustomerCost(Customer c){
        return c.getBookedRooms().stream()
                .map(roomID -> roomRepository.findById(roomID).orElseThrow(EntityNotFoundException::new).getTotalCost())
                .reduce(0, Integer::sum);
    }

}