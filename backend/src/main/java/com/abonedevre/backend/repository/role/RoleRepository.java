package com.abonedevre.backend.repository.role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abonedevre.backend.entity.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    
}
