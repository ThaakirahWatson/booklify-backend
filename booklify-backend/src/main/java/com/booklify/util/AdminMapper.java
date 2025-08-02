package com.booklify.util;

import com.booklify.domain.Admin;
import com.booklify.domain.RegularUser;
import com.booklify.dto.AdminDto;


import java.time.LocalDateTime;

public class AdminMapper {

    // Convert Admin Entity -> AdminDto
    public static AdminDto toAdminDto(Admin admin) {
            if (admin == null) return null;
            AdminDto dto = new AdminDto();
            dto.setId(admin.getId());
            dto.setFullName(admin.getFullName());
            dto.setEmail(admin.getEmail());
            dto.setDateJoined(admin.getDateJoined());
            dto.setLastLogin(admin.getLastLogin());
            dto.setPermissions(admin.getPermissions());
            return dto;
    }


}
