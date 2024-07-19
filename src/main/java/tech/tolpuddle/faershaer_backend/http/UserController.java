package tech.tolpuddle.faershaer_backend.http;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tolpuddle.faershaer_backend.domain.User;
import tech.tolpuddle.faershaer_backend.domain.UserDbRepo;
import tech.tolpuddle.faershaer_backend.http.dtos.AddUserDto;
import tech.tolpuddle.faershaer_backend.http.dtos.UserDto;
import tech.tolpuddle.faershaer_backend.services.UserAccessor;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    UserAccessor userAccessor;
    UserDbRepo repo;

    public UserController(UserAccessor userAccessor, UserDbRepo repo) {
        this.userAccessor = userAccessor;
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserData() {
        if(userAccessor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserDto.getFromUser(userAccessor.getUser()));
    }

    @PostMapping
    public ResponseEntity<Void> registerNewUser(@RequestBody AddUserDto dto) {
        User user = dto.getUser();
        System.out.println("User looks like: " + user.getId() + " " + user.getName() + " " + user.getPortraitUrl());
        repo.save(user);
        return ResponseEntity.accepted().build();
    }
}
