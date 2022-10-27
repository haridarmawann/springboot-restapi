package hari.darmawan.core.controllers;


import hari.darmawan.core.dto.ResponseBody;
import hari.darmawan.core.dto.UserDto;
import hari.darmawan.core.helpers.Utils;
import hari.darmawan.core.models.entities.User;
import hari.darmawan.core.services.UserService;
import hari.darmawan.core.storage.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StorageService storageService;

    private Environment environment;
    
    protected Utils utils = new Utils();


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



            String baseUrl = utils.baseUrl(environment);
            for (User item : users) {
                String ImageLink = baseUrl+"/uploads/media/profile/" + item.getPhotos();
                ArrayList<UserDto> user = new ArrayList<>(Collections.singletonList(new UserDto(item.getId(), item.getEmail(), item.getFirstName(), item.getLastName(), ImageLink)));
                userDtoList.addAll(user);
            }
            ResponseBody responseBody = new ResponseBody(200, "Data Succesfully retreived", userDtoList);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);

        }catch (Exception e){
            //disini
            ResponseBody responseBody = new ResponseBody(500,e.getMessage(),null);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseBody> createUser(
            @RequestParam String email,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam MultipartFile photos
    ){
        String imageFile = null;
        try {
            if (photos != null){
                String filetype = FilenameUtils.getExtension(photos.getOriginalFilename());
                imageFile = "profile-" + firstName.toLowerCase() + "."+ filetype;
                storageService.saveFile(photos,imageFile,"media/profile");
            }
            User user = new User(email,firstName,lastName,imageFile);
            userService.save(user);
            ResponseBody responseBody = new ResponseBody(200, "Data Succesfully retreived",user);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);

            }catch (Exception e){
            ResponseBody responseBody = new ResponseBody(500,e.getMessage(),null);
            return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        @GetMapping("users/{id}")
        public ResponseEntity<ResponseBody> findById(@PathVariable("id") String id){
            try {
                Optional<User> user = userService.findByid(id);

                if (user.isPresent()){
                    User _user = user.get();
                    String baseUrl = utils.baseUrl(environment);
                    String imageLink = baseUrl +"uploads/media/profile" +_user.getPhotos();
                    UserDto userDto =new UserDto(_user.getId(), _user.getEmail(), _user.getFirstName(),_user.getLastName(),imageLink);
                    ResponseBody responseBody = new ResponseBody(200, "Data Succesfully retreived",userDto);
                    return new ResponseEntity<>(responseBody, HttpStatus.OK);
                }else {
                    ResponseBody responseBody = new ResponseBody(200, "Data is Empy",null);
                    return new ResponseEntity<>(responseBody, HttpStatus.OK);
                }
            } catch (Exception e) {
                ResponseBody responseBody = new ResponseBody(500,e.getMessage(),null);
                return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

}
