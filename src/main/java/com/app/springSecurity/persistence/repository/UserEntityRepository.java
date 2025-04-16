
package com.app.springSecurity.persistence.repository;

import com.app.springSecurity.persistence.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long>{
    
    Optional<UserEntity> findUserEntityByUsername(String username);
    boolean existsByUsername(String username);
    
}
