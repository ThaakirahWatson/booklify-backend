package com.booklify.service;

import com.booklify.domain.Admin;
import com.booklify.domain.RegularUser;
import org.springframework.data.mapping.model.AbstractPersistentProperty;

import java.util.List;

public interface IAdminService extends IService<Admin, Long> {

    List<Admin> findByEmail(String email);
    List<Admin> findByFullName(String fullName);

    // Finding Regular Users by Email
    List<RegularUser> findAllRegularUsersByEmail(String email);
    List<RegularUser> findAllRegularUsersByFullName(String fullName);

    //Logging in Admin
    Admin login(String email, String password);

    //permissions
    void deleteRegularUserById(Long id);
    void updateRegularUserById(Long id, RegularUser regularUser);  //Editing a Regular User's details
    Void viewAllRegularUsers();

    Void approveBookListing(Long bookId);
    Void rejectBookListing(Long bookId);
    Void viewAllBookListings();
    Void deleteBookListingById(Long bookId);
    Void editBookListingById(Long bookId, RegularUser regularUser);

    Void viewSalesReport();
    Void viewUserActivityReport();
    Void financialReport();

}
