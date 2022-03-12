package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.User;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.UserDTO;
import pdp.uz.pricticelesson11.repository.UserRepository;
import pdp.uz.pricticelesson11.repository.WarehouseRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<User> getAll(){
        List<User> all = userRepository.findAll();
        return all;
    }
    public User getById(Integer id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            User user = byId.get();
            return user;
        }
        return new User();
    }
    public ApiResponse addUser(UserDTO userDTO){
        boolean exists = userRepository.existsByPhoneNumberAndPassword(userDTO.getPhoneNumber(), userDTO.getPassword());
        if (exists){
            return new ApiResponse("This is PhoneNumber and Password already exists", false);
        }
        User user=new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCode(UUID.randomUUID().toString());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());

        Set<Warehouse> warehouseSet=new HashSet<>();
        Integer[] warehouseList1 = userDTO.getWarehouseList();
        for (Integer integer : warehouseList1) {
            Warehouse warehouse = warehouseRepository.findById(integer).get();
            warehouseSet.add(warehouse);
        }
        user.setWarehouseList(warehouseSet);
        userRepository.save(user);
        return new ApiResponse("added", true);
    }
    public ApiResponse edit(Integer id, UserDTO userDTO){
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("not found user id", false);
        boolean exists = userRepository.existsByPhoneNumberAndPassword(userDTO.getPhoneNumber(), userDTO.getPassword());
        if (exists){
            return new ApiResponse("This is PhoneNumber and Password already exists", false);
        }
        User user = byId.get();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCode(UUID.randomUUID().toString());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());

        Set<Warehouse> warehouseSet=new HashSet<>();
        Integer[] warehouseList1 = userDTO.getWarehouseList();
        for (Integer integer : warehouseList1) {
            Warehouse warehouse = warehouseRepository.findById(integer).get();
            warehouseSet.add(warehouse);
        }
        user.setWarehouseList(warehouseSet);
        userRepository.save(user);
        return new ApiResponse("added", true);

    }
    public ApiResponse delete(Integer id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            userRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found user id", false);
    }
}
