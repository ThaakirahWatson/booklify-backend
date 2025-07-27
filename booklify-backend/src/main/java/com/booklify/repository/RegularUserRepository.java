package com.booklify.repository;

import com.booklify.domain.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegularUserRepository extends JpaRepository<RegularUser, Long> {

    List<RegularUser> findByEmail(String email);
    List<RegularUser> findByFullName(String fullName);

}
