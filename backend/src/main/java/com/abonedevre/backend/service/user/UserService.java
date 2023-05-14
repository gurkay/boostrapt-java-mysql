package com.abonedevre.backend.service.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abonedevre.backend.entity.Role;
import com.abonedevre.backend.entity.User;
import com.abonedevre.backend.exception.UserNotFoundException;
import com.abonedevre.backend.paging.PagingAndSortingHelper;
import com.abonedevre.backend.repository.role.RoleRepository;
import com.abonedevre.backend.repository.user.UserRepository;
import com.abonedevre.backend.repository.user.UserRepositoryCrud;

@Service
@Transactional
public class UserService {

    public static final Integer USER_PER_PAGE = 4;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    public List<User> listAll() {

        return (List<User>) userRepository.findAll(Sort.by("firstName").ascending());
    }

    public void listByPage(Integer pageNum, PagingAndSortingHelper helper){

        helper.listEntities(pageNum, USER_PER_PAGE, userRepository);
    }

    public List<Role> listRoles() {

        return (List<Role>) roleRepository.findAll();
    }

    public User save(User user) {
        boolean isUpdatingUser = (user.getId() != null);

        if (isUpdatingUser) {

            // User existingUser = userRepository.findById(user.getId()).get();
            User existingUser = userRepositoryCrud.findById(user.getId()).get();

            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }
        } else {
            encodePassword(user);
        }

        return userRepositoryCrud.save(user);
    }

    public User updateAccount(User userInForm){
        User user = userRepositoryCrud.findById(userInForm.getId()).get();

        if(!userInForm.getPassword().isEmpty()){
            user.setPassword(userInForm.getPassword());
            encodePassword(user);
        }

        if(userInForm.getPhotos() != null){
            user.setPhotos(userInForm.getPhotos());
        }

        user.setFirstName(userInForm.getFirstName());
        user.setLastName(userInForm.getLastName());
        
        return userRepositoryCrud.save(user);
    }

    private void encodePassword(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            return true;
        }

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            if (user != null) {
                return false;
            }
        } else {
            if (user.getId() != id) {
                return false;
            }
        }

        return true;
    }

    public User getUserId(Integer id) throws UserNotFoundException {
        try {
            return userRepositoryCrud.findById(id).get();
        } catch (NoSuchElementException exception) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepository.countById(id);
        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }

        userRepositoryCrud.deleteById(id);
    }

    public void updateUserEnabledStatus(Integer id, boolean enabled){
        userRepository.updateEnabledStatus(id, enabled);
    }
}
