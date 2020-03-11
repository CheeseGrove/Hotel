package com.ecutbildning.hotelmanager.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PostMapping("/{id}")
    public Customer update(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}/setBookedRooms")
    public Customer setBookedRooms(@PathVariable String id, ArrayList<String> bookedList){
        return customerService.setBookedRooms(id, bookedList);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        customerService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        customerService.deleteAll();
    }

}
