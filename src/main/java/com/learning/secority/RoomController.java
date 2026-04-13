package com.learning.secority;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    public String addRoom() {
        return "Room added";
    }

    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id) {
        return "Room fetched for id: " + id;
    }

    @GetMapping
    public String getRoom() {
        return "All rooms";
    }
}
