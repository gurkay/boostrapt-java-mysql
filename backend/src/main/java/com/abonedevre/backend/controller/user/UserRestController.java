package com.abonedevre.backend.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abonedevre.backend.service.user.UserService;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(Integer id, String email) {
        return userService.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }

}