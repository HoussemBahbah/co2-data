package com.project.emissionsapi.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
