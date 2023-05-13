package com.abonedevre.backend.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.abonedevre.backend.entity.Role;
import com.abonedevre.backend.entity.User;
import com.abonedevre.backend.repository.user.UserRepositoryCrud;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepositoryCrud userRepositoryCrud;

    @Test
    public void testCreateNewUserWithRole() {

        // User user = new User("gurkaybasyigit@gmail.com", "gurkay123456", "Gürkay", "BAŞYİĞİT");
        User user = new User("Birlikyonetici@gmail.com", "gurkay123456", "Birlik_Yonetici", "lastName");
        Role role = new Role(5);
        // Role roleBaskanlikYonetici = new Role(2);
        user.addRole(role);
        // user.addRole(roleBaskanlikYonetici);

        User savedUser = userRepositoryCrud.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
