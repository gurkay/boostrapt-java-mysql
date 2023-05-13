package com.abonedevre.backend.role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.abonedevre.backend.entity.Role;
import com.abonedevre.backend.repository.role.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateFirstRole() {
        Role roleAdmin = new Role("Admin", "Manage Everything");
        Role savedRole = roleRepository.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateRestRole() {
        Role roleBaskanlikYonetici = new Role("BaskanlikYonetici", "Birlik Yoneticisi ve Kullanicilari Tanimlar");
        Role roleBaskanlikMudur = new Role("BaskanlikMudur", "Tum Hat Raporlarini Gorebilir");
        Role roleBaskanlikBirimKullanici = new Role("BaskanlikBirimKullanici", "Kendi Kisim İle İlgili Hatlari Gorur");
        Role roleBirlikYonetici = new Role("BirlikYonetici", "Kendi Birlik Kullanicisi Tanimlar");
        Role roleBirlikKullanici = new Role("BirlikKullanici", "Kendi Hatlarini Gorur");

        roleRepository.saveAll(List.of(roleBaskanlikYonetici, roleBaskanlikMudur, roleBaskanlikBirimKullanici, roleBirlikYonetici, roleBirlikKullanici));
    }

    @Test
    public void testDeleteRoles() {
        roleRepository.deleteAll();
    }

}
