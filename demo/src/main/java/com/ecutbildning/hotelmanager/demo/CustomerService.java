package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public List<Customers> findAll() {
        return customerRepository.findAll();
    }

    public Customers findById(String id) {
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Customers save(Customers c) {
        return customerRepository.save(c);
    }

    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }

}
