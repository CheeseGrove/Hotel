package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.exception.EntityNotFoundException;
import com.ecutbildning.hotelmanager.rooms.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {

    @Autowired
    public RoomsService roomsService;

    @GetMapping("/all")
    public List<Rooms> findAll() {
        return roomsService.findAll();
    }

    @GetMapping("/{id}")
    public Rooms findById(@PathVariable String id) {
        return roomsService.findById(id);
    }


    @PostMapping
    public Rooms create(@RequestParam("type") String type) {
        return roomsService.create(type);
    }

    @PostMapping("/{id}")
    public Rooms update(@RequestBody Rooms rooms) {
        return roomsService.save(rooms);
    }

    @PostMapping ("/{id}/setBooked")
    public void setBooked(@PathVariable String id, @RequestParam("bk") boolean booked) {
        roomsService.changeBooked(id, booked);
    }

    @PostMapping("/{id}/addFruit")
    public void addFruit(@PathVariable String id, @RequestParam("fruitlist") List<String> fruitlist){
        roomsService.addFood(id, fruitlist);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        roomsService.deleteById(id);
    }

    @DeleteMapping("/")
    public void delete(){
        roomsService.deleteAll();
    }

}
