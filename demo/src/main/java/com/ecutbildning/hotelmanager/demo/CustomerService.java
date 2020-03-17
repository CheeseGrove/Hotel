package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import com.ecutbildning.hotelmanager.rooms.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(String id) {
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Customer save(Customer c) {
        long diff = c.getLeavingDate().getTime() - c.getArrivingDate().getTime();
        c.setDaysStaying((int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        if(roomRepository.findById(c.getBookedRooms().stream().findFirst().toString()).isPresent()) {
            Room room = roomRepository.
                    findById(c.getBookedRooms().stream().findFirst().orElseThrow(EntityNotFoundException::new))
                    .orElseThrow(EntityNotFoundException::new);
            room.setDaysBooked(c.getDaysStaying());
            room.updateTotalCost();
            room.setBooked(true);
            roomRepository.save(room);
            room.setTotalCost(5000);
            return customerRepository.save(c);
        }

        return customerRepository.save(c);
    }

    public void deleteById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customer.getBookedRooms().forEach(roomID -> {
            roomService.changeBooked(roomID, false);
        });
        customerRepository.deleteById(id);
    }


    public Customer addBookedRooms (String id, String roomID){
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Room room = roomRepository.findById(roomID).orElseThrow(EntityNotFoundException::new);
        room.setDaysBooked((int) ChronoUnit.DAYS.between(customer.getArrivingDate().toInstant(), customer.getLeavingDate().toInstant()));
        room.setTotalCost(room.getDaysBooked() * room.getChargePerDay() + room.getFruitCharge());
        room.setBooked(true);
        roomRepository.save(room);
        customer.getBookedRooms().add(room.getId());
        customer.setBillToPay(customer.getBillToPay() + room.getTotalCost());
        return customerRepository.save(customer);
    }

    public Customer removeBookedRooms (String id, String roomID){
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Room room = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customer.setBillToPay(customer.getBillToPay() - room.getTotalCost());
        roomService.changeBooked(room.getId(), false);
        return customerRepository.save(customer);
    }

    public Customer setBookedRooms(String id, ArrayList<String> roomList){
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customer.setBookedRooms(roomList);
        return customerRepository.save(customer);
    }

    public void deleteAll(){
        customerRepository.deleteAll();
        roomRepository.findAll().forEach(room -> roomService.changeBooked(room.getId(), false));
    }



}
