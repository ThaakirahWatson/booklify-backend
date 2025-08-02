package com.booklify.controller;

import com.booklify.domain.Admin;
import com.booklify.domain.RegularUser;
import com.booklify.dto.AdminDto;
import com.booklify.dto.RegularUserDto;
import com.booklify.service.AdminService;
import com.booklify.util.JwtUtil;
import com.booklify.util.RegularUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
        Admin admin = AdminDto.toEntity(adminDto);
        Admin saved = adminService.save(admin);
        return ResponseEntity.ok(AdminDto.fromEntity(saved));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegularUserController.LoginRequest request) {
        Admin admin = adminService.login(request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(admin.getEmail());
        return ResponseEntity.ok(token);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<AdminDto> getAdmin(@PathVariable Long id) {
        Admin admin = adminService.findById(id);
        return ResponseEntity.ok(AdminDto.fromEntity(admin));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable Long id, @RequestBody AdminDto adminDto) {
        Admin existing = adminService.findById(id);

        Admin updated = new Admin.AdminBuilder()
                .copy(existing)
                .setFullName(adminDto.getFullName())
                .setEmail(adminDto.getEmail())
                .setPassword(adminDto.getPassword())
                .setLastLogin(adminDto.getLastLogin())
                .setDateJoined(adminDto.getDateJoined())
                .setPermissions(adminDto.getPermissions())
                .build();

        Admin saved = adminService.update(updated);
        return ResponseEntity.ok(AdminDto.fromEntity(saved));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<RegularUserDto>> viewAllRegularUsers() {
        List<RegularUser> users = adminService.viewAllRegularUsers();
        List<RegularUserDto> dtos = users.stream()
                .map(RegularUserMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteRegularUser(@PathVariable Long id) {
        adminService.deleteRegularUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateRegularUser(@PathVariable Long id, @RequestBody RegularUserDto userDto) {
        RegularUser updated = RegularUserMapper.toEntity(userDto);
        adminService.updateRegularUserById(id, updated);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/email")
    public ResponseEntity<List<RegularUserDto>> searchByEmail(@RequestParam String email) {
        List<RegularUser> users = adminService.findAllRegularUsersByEmail(email);
        List<RegularUserDto> dtos = users.stream()
                .map(RegularUserMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/users/name")
    public ResponseEntity<List<RegularUserDto>> searchByName(@RequestParam String fullName) {
        List<RegularUser> users = adminService.findAllRegularUsersByFullName(fullName);
        List<RegularUserDto> dtos = users.stream()
                .map(RegularUserMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
