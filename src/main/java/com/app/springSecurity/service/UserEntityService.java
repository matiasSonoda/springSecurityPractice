
package com.app.springSecurity.service;

import com.app.springSecurity.persistence.entity.RoleEnum;
import com.app.springSecurity.persistence.entity.UserEntity;
import com.app.springSecurity.persistence.entity.dto.UserEntityDto;
import com.app.springSecurity.persistence.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {
    @Autowired
    private UserEntityRepository userEntityRepository;
    
    public UserEntityDto createUser(UserEntityDto request){
        if(userEntityRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("The username does exists: " + request.getUsername());
        }
        String password = new BCryptPasswordEncoder().encode(request.getPassword());
        request.setPassword(password);
        
        UserEntity creatingUser = new UserEntity();
        creatingUser.setUsername(request.getUsername());
        creatingUser.setPassword(password);
        try{
        RoleEnum role = RoleEnum.valueOf(request.getRole().toUpperCase());
        creatingUser.setRole(role);
        }catch(IllegalArgumentException e){
            throw new RuntimeException("the role does not exists: " + request.getRole());
        }
        
        UserEntity aux = userEntityRepository.save(creatingUser);
        UserEntityDto response = new UserEntityDto();
        response.setUsername(aux.getUsername());
        response.setPassword(request.getPassword());
        response.setRole(aux.getRole().toString());
        return response;
    }
}
