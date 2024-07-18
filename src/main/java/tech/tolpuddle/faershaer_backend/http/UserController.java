package tech.tolpuddle.faershaer_backend.http;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tolpuddle.faershaer_backend.http.dtos.AddUserDto;
import tech.tolpuddle.faershaer_backend.http.dtos.UserDto;
import tech.tolpuddle.faershaer_backend.services.UserAccessor;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    UserAccessor userAccessor;

    public UserController(UserAccessor userAccessor) {
        this.userAccessor = userAccessor;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserData() {
        if(userAccessor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserDto.getFromUser(userAccessor.getUser()));
    }
}
