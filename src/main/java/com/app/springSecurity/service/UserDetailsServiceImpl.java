
package com.app.springSecurity.service;

import com.app.springSecurity.persistence.entity.RolePermissionMapping;
import com.app.springSecurity.persistence.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.app.springSecurity.persistence.repository.UserEntityRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserEntityRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));
        
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        
        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(userEntity.getRole().toString())));
        
        RolePermissionMapping.getPermissions(userEntity.getRole()).stream()
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.name())));
        
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }
}
