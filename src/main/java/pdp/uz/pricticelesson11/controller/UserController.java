package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.User;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.UserDTO;
import pdp.uz.pricticelesson11.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUser(){
        return userService.getAll();

    }
    @GetMapping("/ById/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getById(id);

    }
    @PostMapping("/add")
    public ApiResponse addUser(@RequestBody UserDTO userDTO){
        ApiResponse apiResponse = userService.addUser(userDTO);
        return apiResponse;
    }
    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody UserDTO userDTO){
        return userService.edit(id, userDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleted(@PathVariable Integer id){
        return userService.delete(id);
    }
}
