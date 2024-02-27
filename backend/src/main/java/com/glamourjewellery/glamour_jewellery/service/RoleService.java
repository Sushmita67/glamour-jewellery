package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    String save(Role role);

    List<Role> getAll();

    Optional<Role> getById(Integer id);

    void deleteById(Integer id);

    String update(Integer id, Role role);
}
