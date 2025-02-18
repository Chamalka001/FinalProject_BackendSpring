package com.example.Home.controller;


import com.example.Home.dto.HouseDTO;
import com.example.Home.dto.ResponseDTO;
import com.example.Home.service.HouseService;
import com.example.Home.util.VarList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/v1/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private ResponseDTO responseDTO;

    @CrossOrigin(origins = "http://localhost:5173")
                @PostMapping("/saveHouse")
                public ResponseEntity<ResponseDTO> saveHouse(
                        @RequestParam("file") MultipartFile file,
                        @RequestParam("houseDTO") String houseDTOJson) {
                    try {
                        HouseDTO houseDTO = new ObjectMapper().readValue(houseDTOJson, HouseDTO.class);
                        String res = houseService.saveHouse(houseDTO, file);

                        if (VarList.RSP_SUCCESS.equals(res)) {
                            responseDTO.setCode(VarList.RSP_SUCCESS);
                            responseDTO.setMessage("House saved successfully.");
                            responseDTO.setContent(houseDTO);
                            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
                        } else if (VarList.RSP_DUPLICATED.equals(res)) {
                            responseDTO.setCode(VarList.RSP_DUPLICATED);
                            responseDTO.setMessage("House already registered.");
                            responseDTO.setContent(houseDTO);
                            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
                        } else {
                            responseDTO.setCode(VarList.RSP_FAIL);
                            responseDTO.setMessage("Error saving house.");
                            responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/updateHouse")
    public ResponseEntity<ResponseDTO> updateHouse(
            @RequestParam("houseDTO") String houseDTOJson,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            HouseDTO houseDTO = new ObjectMapper().readValue(houseDTOJson, HouseDTO.class);
            String result = houseService.updateHouse(houseDTO, file);

            if (VarList.RSP_SUCCESS.equals(result)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("House updated successfully.");
                responseDTO.setContent(houseDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else if (VarList.RSP_NO_DATA_FOUND.equals(result)) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No house found with the provided ID.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Failed to update house.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/getAllHouses")
    public ResponseEntity<ResponseDTO> getAllHouses() {
        try {
            List<HouseDTO> houseDTOList = houseService.getAllHouses();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("House retrieved successfully.");
            responseDTO.setContent(houseDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/searchHouse/{houseID}")
    public ResponseEntity<ResponseDTO> searchHouse(@PathVariable int houseID) {
        try {
            HouseDTO houseDTO = houseService.searchHouse(houseID);

            if (houseDTO != null) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("House found.");
                responseDTO.setContent(houseDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No house found with this ID.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/deleteHouse/{houseID}")
    public ResponseEntity<ResponseDTO> deleteHouse(@PathVariable int houseID) {
        try {
            String result = houseService.deleteHouse(houseID);

            if (VarList.RSP_SUCCESS.equals(result)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("House deleted successfully.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No House found with this ID.");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
