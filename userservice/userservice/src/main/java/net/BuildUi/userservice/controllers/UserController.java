package net.BuildUi.userservice.controllers;

import lombok.RequiredArgsConstructor;
import net.BuildUi.userservice.models.UserInfoDto;
import net.BuildUi.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/v1/")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createOrUpdate")
    public ResponseEntity<?> createOrUpdate(@RequestBody UserInfoDto userInfoDto){
        try{
            UserInfoDto user = userService.createOrUpdate(userInfoDto);
            return new ResponseEntity<>(user , HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestHeader("X-User-Id") String userId){
        try{
            UserInfoDto user = userService.getUser(userId);
            return new ResponseEntity<>(user , HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Boolean> checkHealth(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
