package com.booklify.util;

import com.booklify.domain.Admin;
import com.booklify.domain.RegularUser;
import com.booklify.dto.AdminDto;
import com.booklify.dto.CreateAdminRequestDto;

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

//    // Convert CreateAdminRequestDto -> Admin Entity
//    public static Admin toAdminEntity(CreateAdminRequestDto createDto) {
//        if (createDto == null) return null;
//        return new Admin.AdminBuilder()
//                .setFullName(createDto.getFullName())
//                .setEmail(createDto.getEmail())
//                .setPassword(createDto.getPassword()) // Password will be encoded in the service
//                .setDateJoined(LocalDateTime.now())
//                .build();
//    }

//    // Convert RegularUser Entity -> RegularUserDto (UNCHANGED)
//    public static RegularUserDto toRegularUserDto(RegularUser user) {
//     if (user == null) return null;
//    RegularUserDto dto = new RegularUserDto();
//        dto.setId(user.getId());
//        dto.setFullName(user.getFullName());
//        dto.setEmail(user.getEmail());
//        dto.setDateJoined(user.getDateJoined());
//        return dto;
//
//    }
//
//    // Convert List<RegularUser> -> List<RegularUserDto> (UNCHANGED)
//    public static List<RegularUserDto> toRegularUserDtoList(List<RegularUser> users) {
//        return users.stream().map(AdminMapper::toRegularUserDto).collect(Collectors.toList());
//    }
}
