package com.glamourjewellery.glamour_jewellery.repo;

import com.glamourjewellery.glamour_jewellery.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

}
