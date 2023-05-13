package com.abonedevre.backend.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.abonedevre.backend.entity.User;

public interface UserRepositoryCrud extends CrudRepository<User, Integer> {
    
}
