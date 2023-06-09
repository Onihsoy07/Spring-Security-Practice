package com.example.ttestt.controller.api;

import com.example.ttestt.data.dto.UsersDto;
import com.example.ttestt.data.entity.Users;
import com.example.ttestt.data.mapping.UsersMapping;
import com.example.ttestt.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UsersApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(UsersApiController.class);

    private final UsersService usersService;

    @PostMapping("/api/user/join")
    public ResponseEntity<UsersDto> join(@RequestBody Users users) {
        LOGGER.info("/api/user/join 실행");
        UsersDto dto = usersService.postUsers(users);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UsersDto> getUsers(@PathVariable final Long id) {
        UsersDto dto = usersService.getUsers(id);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("/user")
    public ResponseEntity<UsersDto> postUsers(@RequestBody final UsersDto usersDto) {
        Users users = UsersMapping.convertToUser(usersDto);
        UsersDto dto = usersService.postUsers(users);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UsersDto> updateUsers(@PathVariable final Long id,
                                                @RequestBody final UsersDto usersDto) {
        UsersDto dto = usersService.updateUsers(id, usersDto);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable final Long id) {
        usersService.deleteUsers(id);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("id : %d 의 Users를 삭제하였습니다.", id));
    }

}
