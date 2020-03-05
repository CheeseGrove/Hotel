package com.ecutbildning.hotelmanager.demo;

import com.ecutbildning.hotelmanager.rooms.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomsController {
    @Autowired
    private RoomsService roomsService;

    @GetMapping("/all")
    public List<Rooms> findAll() {
        return roomsService.findAll();
    }

    @GetMapping("/{id}")
    public Rooms findById(@PathVariable String id) {
        return roomsService.findById(id);
    }

    @PostMapping
    public Rooms create(@RequestBody Rooms rooms) {
        return roomsService.save(rooms);
    }

    @PostMapping("/{id}")
    public Rooms update(@RequestBody Rooms rooms) {
        return roomsService.save(rooms);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        roomsService.deleteById(id);
    }

}
