package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.demo.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {}
