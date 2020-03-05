package com.ecutbildning.hotelmanager.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customers> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customers findById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @PostMapping
    public Customers create(@RequestBody Customers customer) {
        return customerService.save(customer);
    }

    @PostMapping("/{id}")
    public Customers update(@RequestBody Customers customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        customerService.deleteById(id);
    }

}
