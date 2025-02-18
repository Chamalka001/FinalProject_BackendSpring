package com.example.Home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private int userID;
    private String contact;
    private String email;
    private String name;
    private String password;
}
