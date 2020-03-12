package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import com.ecutbildning.hotelmanager.rooms.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        long diff = c.leavingDate.getTime() - c.getArrivingDate().getTime();
        int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        roomRepository.findById(c.getBookedRooms().get(0).toString()).ifPresent((room) -> c.setBillToPay(room.getChargePerDay() * days));
        return customerRepository.save(c);
    }

    public void deleteById(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customer.getBookedRooms().stream().forEach(roomID -> roomService.changeBooked(roomID, false));
        customerRepository.deleteById(id);
    }


    public Customer addBookedRooms (String id, String roomID){
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customer.getBookedRooms().add(roomID);
        Room room = roomRepository.findById(roomID).orElseThrow(EntityNotFoundException::new);
        customer.setBillToPay(
                customer.getBillToPay()
                + Math.toIntExact(customer.getLeavingDate().getTime() - customer.getArrivingDate().getTime())
                * room.getChargePerDay()
        );
        return customerRepository.save(customer);
    }

    public Customer removeBookedRooms (String id, ArrayList<String> roomList){
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customer.getBookedRooms().addAll(roomList.stream()
                .filter(roomList::contains)
                .collect(Collectors.toList()));
        return customerRepository.save(customer);
    }

    public Customer setBookedRooms(String id, ArrayList<String> roomList){
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customer.setBookedRooms(roomList);
        return customerRepository.save(customer);
    }

    public void deleteAll(){
        customerRepository.deleteAll();
    }



}
