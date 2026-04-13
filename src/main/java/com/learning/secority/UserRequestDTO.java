package com.learning.secority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    private String username;
    private String password;
    private String role;
}
