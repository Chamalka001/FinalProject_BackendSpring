package com.example.Home.service;

import com.example.Home.dto.UserDTO;
import com.example.Home.entity.User;
import com.example.Home.repository.UserRepo;
import com.example.Home.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String saveUser(UserDTO userDTO){
        if(userRepo.existsById(userDTO.getUserID())){
            return VarList.RSP_DUPLICATED;
        }else{
            userRepo.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateUser(UserDTO userDTO){
        if(userRepo.existsById(userDTO.getUserID())){
            userRepo.save(modelMapper.map(userDTO,User.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<UserDTO> getAllUsers(){
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<ArrayList<UserDTO>>(){
        }.getType());
    }


    public UserDTO searchUser(int userID){
        if(userRepo.existsById(userID)){
            User user = userRepo.findById(userID).orElse(null);
            return modelMapper.map(user,UserDTO.class);
        }else{
            return null;
        }
    }

    public String deleteUser(int userID){
        if(userRepo.existsById(userID)){
            userRepo.deleteById(userID);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
