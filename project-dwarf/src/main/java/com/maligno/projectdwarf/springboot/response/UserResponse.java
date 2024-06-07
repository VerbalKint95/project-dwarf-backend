package com.maligno.projectdwarf.springboot.response;


import java.time.LocalDate;

import com.maligno.projectdwarf.springboot.security.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

//attributes
    private long id;
    private String username;
    private Role role;
}
