
package com.app.springSecurity.persistence.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@NoArgsConstructor
public class UserEntityDto {
    
    private String username;
    private String password;
    private String role;
    
}
