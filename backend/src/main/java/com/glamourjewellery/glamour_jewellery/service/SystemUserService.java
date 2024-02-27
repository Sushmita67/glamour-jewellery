package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
import com.glamourjewellery.glamour_jewellery.pojo.NewPasswordPojo;
import com.glamourjewellery.glamour_jewellery.pojo.SystemUserPojo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SystemUserService {

    String save(SystemUserPojo systemUserPojo);

    List<SystemUser> getAll();

    Optional<SystemUser> getByEmail(String email);

    void deleteById(Long id);

    Optional<SystemUser> getById(Long id);

    String update(Long id, SystemUserPojo systemUserPojo);

    List<Map<String, Object>> getAllUsersWithoutPassword();

    String setNewPassword(NewPasswordPojo newPasswordPojo);

}
