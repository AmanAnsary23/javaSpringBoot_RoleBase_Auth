package com.learning.secority;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@EnableMethodSecurity
public class RoomController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addRoom() {
        return "Room added";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF' ,'GUEST')")
    public String getRoomById(@PathVariable Long id) {
        return "Room fetched for id: " + id;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN' , 'STAFF')")
    public String getRoom() {
        return "All rooms";
    }
}
