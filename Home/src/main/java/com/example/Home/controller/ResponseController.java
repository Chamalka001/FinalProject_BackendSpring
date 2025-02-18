package com.example.Home.controller;


import com.example.Home.dto.ResponseNDTO;
import com.example.Home.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/response")
@RestController
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @GetMapping("/getResponses")
    public List<ResponseNDTO> getResponses(){
        return responseService.getAllResponses();
    }

    @PostMapping("/saveResponse/{revid}")
    public ResponseNDTO saveResponse(@PathVariable Long revid, @RequestBody ResponseNDTO responseNDTO){
        return responseService.saveResponse(revid, responseNDTO);
    }

    @PutMapping("/updateResponse")
    public ResponseNDTO updateResponse(@RequestBody ResponseNDTO responseNDTO){
        return responseService.updateResponse(responseNDTO);
    }

    @DeleteMapping("/deleteResponse")
    public boolean deleteResponse(@RequestBody ResponseNDTO responseNDTO){
        return responseService.deleteResponse(responseNDTO);
    }

    @GetMapping("/getResponseById/{resId}")
    public ResponseNDTO getResponseById(@PathVariable Long resId){
        return responseService.getResponseById(resId);
    }

    // In ResponseController.java

    @GetMapping("/getResponsesByReviewId/{revid}")
    public List<ResponseNDTO> getResponsesByReviewId(@PathVariable Long revid) {
        return responseService.getResponsesByReviewId(revid);
    }

}
