package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(String id) {
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Customer save(Customer c) {
        return customerRepository.save(c);
    }

    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }


    public Customer addBookedRooms (String id, ArrayList<String> roomList){
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customer.getBookedRooms().addAll(roomList.stream()
                .filter(elem -> !customer.getBookedRooms().contains(elem))
                .collect(Collectors.toList()));
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
