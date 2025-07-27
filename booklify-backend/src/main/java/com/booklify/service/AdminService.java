package com.booklify.service;

import com.booklify.domain.Admin;
import com.booklify.domain.RegularUser;
import com.booklify.repository.AdminRepository;
import com.booklify.repository.RegularUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RegularUserRepository regularUserRepository;

    @Override
    public Admin save(Admin entity) {
        return adminRepository.save(entity);
    }

    @Override
    public Admin findById(Long aLong) {
        return adminRepository.findById(aLong)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + aLong));
    }

    @Override
    public Admin update(Admin entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {
        if (!adminRepository.existsById(aLong)) {
            throw new RuntimeException("Admin not found with id: " + aLong);
        }
        adminRepository.deleteById(aLong);
    }
    @Override
    public List<Admin> findByEmail(String email) {
        return List.of();
    }

    @Override
    public List<Admin> findByFullName(String fullName) {
        return List.of();
    }

    @Override
    public List<RegularUser> findAllRegularUsersByEmail(String email) {
        return List.of();
    }

    @Override
    public List<RegularUser> findAllRegularUsersByFullName(String fullName) {
        return List.of();
    }

    @Override
    public Admin login(String email, String password) {
        return null;
    }

    @Override
    public void deleteRegularUserById(Long id) {

    }

    @Override
    public void updateRegularUserById(Long id, RegularUser regularUser) {

    }

    @Override
    public Void viewAllRegularUsers() {
        return null;
    }

    @Override
    public Void approveBookListing(Long bookId) {
        return null;
    }

    @Override
    public Void rejectBookListing(Long bookId) {
        return null;
    }

    @Override
    public Void viewAllBookListings() {
        return null;
    }

    @Override
    public Void deleteBookListingById(Long bookId) {
        return null;
    }

    @Override
    public Void editBookListingById(Long bookId, RegularUser regularUser) {
        return null;
    }

    @Override
    public Void viewSalesReport() {
        return null;
    }

    @Override
    public Void viewUserActivityReport() {
        return null;
    }

    @Override
    public Void financialReport() {
        return null;
    }


}
