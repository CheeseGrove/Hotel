package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.rooms.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    public RoomService roomService;

    @GetMapping("/all")
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable String id) {
        return roomService.findById(id);
    }

    @PostMapping
    public Room create(@RequestParam("type") String type) {
        return roomService.create(type);
    }

    @PostMapping("/{id}")
    public Room update(@PathVariable String id, @RequestBody Room room) {
        return roomService.save(id, room);
    }

    @PostMapping ("/{id}/setBooked")
    public Room setBooked(@PathVariable String id, @RequestParam("bk") boolean booked) {
        return roomService.changeBooked(id, booked);
    }

    @PostMapping("/{id}/addFruit")
    public void addFruit(@PathVariable String id, @RequestParam("fruit") String fruit){
        roomService.addFood(id, fruit);
    }

    @PutMapping("/{id}/setFruit")
    public void setFruit(@PathVariable String id, @RequestParam("fruitlist") ArrayList<String> fruitList){
        roomService.setFood(id, fruitList);
    }

    @DeleteMapping("/{id}/deleteFruit")
    public void removeFruit(@PathVariable String id, @RequestParam("fruitlist") ArrayList<String> fruitList){
        roomService.removeFood(id, fruitList);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        roomService.deleteById(id);
    }

    @DeleteMapping("/")
    public void delete(){
        roomService.deleteAll();
    }

}
