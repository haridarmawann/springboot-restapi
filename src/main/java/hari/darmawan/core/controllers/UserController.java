package hari.darmawan.core.controllers;


import hari.darmawan.core.dto.ResponseBody;
import hari.darmawan.core.dto.UserDto;
import hari.darmawan.core.models.entities.User;
import hari.darmawan.core.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ResponseBody> getAllUser(){
        try {
            List<User> users = new ArrayList<>();
            List<UserDto> userDtoList = new ArrayList<>();
            users.addAll(userService.findAll());

            if (users.isEmpty()) {
                ResponseBody responseBody = new ResponseBody(204, "Data is empty", null);
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }
            ArrayList<UserDto> userDtos = null;
            for (User item : users) {
                String ImageLink = "/uploads/media/entity/" + item.getPhotos();
                ArrayList<UserDto> user = new ArrayList<>(Collections.singletonList(new UserDto(item.getId(), item.getEmail(), item.getFirstName(), item.getLastName(), ImageLink)));
                userDtoList.addAll(user);
            }
            ResponseBody responseBody = new ResponseBody(200, "Data Succesfully retreived", userDtoList);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);

        }catch (Exception e){
            //disini
            ResponseBody responseBody = new ResponseBody(500,e.getMessage(),null);
            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    public ResponseEntity<ResponseBody> createUser(
//            @RequestParam String id,
//            @RequestParam String email,
//            @RequestParam String firstName,
//            @RequestParam String lastName,
//            @RequestParam MultipartFile photos
//    ){
//        try {
//            if
//        }
//    }
}
