package com.example.demo.user;

import com.example.demo.common.component.Messenger;
import com.example.demo.common.component.PageRequestVo;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDto;
import com.example.demo.user.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
})
@RequiredArgsConstructor
@RequestMapping(path="/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService service;


    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findall(PageRequestVo vo){
        log.info("findAll request : {}", vo);
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping( path = "/detail")
    public ResponseEntity<Optional<UserDto>> findById(@RequestParam Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(service.count());
    }

    @PostMapping(path = "/check-id")
    public ResponseEntity<Boolean> existsById(@RequestParam Long id){
        return  ResponseEntity.ok(service.existsById(id));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Object> save(@RequestBody User user){
        return ResponseEntity.ok(service.save(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Messenger> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PostMapping("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody User user){
        return ResponseEntity.ok(service.modify(user));
    }

    @PostMapping("path = search")
    public ResponseEntity<List<?>> findByName(@RequestParam String name){
        return ResponseEntity.ok(service.findByName(name));
    }

    @PostMapping("path = find-by-job")
    public ResponseEntity<List<?>> findByJob(@RequestParam String job){
        return ResponseEntity.ok(service.findByJob(job));
    }

//    @PutMapping("/modify")
//    public ResponseEntity<Messenger> modify(@RequestBody User user) {
//        service.modify(user);
//        return null;
//    }

    @PostMapping(path = "/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.login(userDto));
    }


}
