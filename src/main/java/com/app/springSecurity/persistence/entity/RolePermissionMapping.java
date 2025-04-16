
package com.app.springSecurity.persistence.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RolePermissionMapping {
    
    public static final Map<RoleEnum,Set<PermissionEnum>> ROLE_PERMISSIONS = new HashMap<>();
         
    static {
        ROLE_PERMISSIONS.put(RoleEnum.ADMIN, EnumSet.allOf(PermissionEnum.class));
        ROLE_PERMISSIONS.put(RoleEnum.USER, EnumSet.of(PermissionEnum.READ, PermissionEnum.CREATE, PermissionEnum.UPDATE));
        ROLE_PERMISSIONS.put(RoleEnum.INVITED, EnumSet.of(PermissionEnum.READ));
    }
    
    public static Set<PermissionEnum> getPermissions(RoleEnum role){
        return ROLE_PERMISSIONS.get(role);
    }
        
    
}
