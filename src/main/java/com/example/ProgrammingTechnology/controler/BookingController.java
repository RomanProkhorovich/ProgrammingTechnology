package com.example.ProgrammingTechnology.controler;

import com.example.ProgrammingTechnology.model.Booking;
import com.example.ProgrammingTechnology.model.Restaurant;
import com.example.ProgrammingTechnology.model.User;
import com.example.ProgrammingTechnology.repository.BookingRepository;
import com.example.ProgrammingTechnology.repository.UserRepository;
import com.example.ProgrammingTechnology.security.SecurityHelper;
import com.example.ProgrammingTechnology.service.RestaurantService;
import com.example.ProgrammingTechnology.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {
    private final UserService userService;
    private final BookingRepository repository;
    private final RestaurantService restaurantService;
    @GetMapping
    @Secured("hasAnyAuthority('Admin','Manager')")
    public ResponseEntity<List<Booking>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }
    @PostMapping
    //@Secured("hasAnyAuthority('Admin','Manager')")
    public ResponseEntity<?> create(@RequestBody Dto dto){
        Restaurant rest = restaurantService.findRestaurantById(dto.restId);
        User user;
        if (dto.userId!=null)
            user = userService.findUserById(dto.userId);
        else
            user=userService.findUserByEmailOrPhone(SecurityHelper.getCurrentUser().getUsername());
        Booking booking= Booking.builder()
                .dateTime(dto.dateTime)
                .personCount(dto.count)
                .restaurant(rest)
                .user(user)
                .build();
        Booking save = repository.save(booking);
        return ResponseEntity.ok("saved");
    }

    @Setter
    @Getter
    static class Dto{
        private Long userId;
        private byte count;
        private Long restId;
        private LocalDateTime dateTime;
    }
}
