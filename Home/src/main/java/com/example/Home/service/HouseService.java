package com.example.Home.service;


import com.example.Home.dto.HouseDTO;
import com.example.Home.entity.House;
import com.example.Home.repository.HouseRepo;
import com.example.Home.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HouseService {

    @Autowired
    private HouseRepo houseRepo;

    @Autowired
    ModelMapper modelMapper;

    private final Path rootLocation = Paths.get("images");

    public String saveHouse(HouseDTO houseDTO, MultipartFile file) {
        try {
            // Handle file upload
            if (file != null && !file.isEmpty()) {
                Files.createDirectories(rootLocation);
                Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                        .normalize().toAbsolutePath();
                file.transferTo(destinationFile);
                houseDTO.setImage(file.getOriginalFilename());
            }

            // Map DTO to entity
            House house = modelMapper.map(houseDTO, House.class);
            // Set postedDate
            house.setPostedDate(LocalDateTime.now());

            houseRepo.save(house);
            return VarList.RSP_SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }
    }


    public String updateHouse(HouseDTO houseDTO, MultipartFile file) {
        if (houseRepo.existsById(houseDTO.getHouseID())) {
            House existingHouse = houseRepo.findById(houseDTO.getHouseID()).orElse(null);

            if (existingHouse != null) {
                // Update fields
                existingHouse.setPrice(houseDTO.getPrice());
                existingHouse.setLocation(houseDTO.getLocation());
                existingHouse.setLandSize(houseDTO.getLandSize());
                existingHouse.setHouseSize(houseDTO.getHouseSize());
                existingHouse.setStoreys(houseDTO.getStoreys());
                existingHouse.setBeds(houseDTO.getBeds());
                existingHouse.setBaths(houseDTO.getBaths());
                existingHouse.setOwnerName(houseDTO.getOwnerName());
                existingHouse.setOwnerContact(houseDTO.getOwnerContact());
                existingHouse.setDescription(houseDTO.getDescription());

                // Handle file upload
                if (file != null && !file.isEmpty()) {
                    try {
                        Files.createDirectories(rootLocation);
                        Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                                .normalize().toAbsolutePath();
                        file.transferTo(destinationFile);
                        existingHouse.setImage(file.getOriginalFilename());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return VarList.RSP_ERROR;
                    }
                } else {
                    existingHouse.setImage(houseDTO.getImage());
                }

                houseRepo.save(existingHouse);
                return VarList.RSP_SUCCESS;
            }
        }
        return VarList.RSP_NO_DATA_FOUND;
    }


    public List<HouseDTO> getAllHouses() {
        List<House> houseList = houseRepo.findAll();
        return modelMapper.map(houseList, new TypeToken<ArrayList<HouseDTO>>(){}.getType());
    }

    public HouseDTO searchHouse(int houseID) {
        if (houseRepo.existsById(houseID)) {
            House house = houseRepo.findById(houseID).orElse(null);
            return modelMapper.map(house, HouseDTO.class);
        } else {
            return null;
        }
    }

    public String deleteHouse(int houseID) {
        if (houseRepo.existsById(houseID)) {
            houseRepo.deleteById(houseID);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }


}
