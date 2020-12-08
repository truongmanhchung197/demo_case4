package com.example.case4.repo;

import com.example.case4.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser getAppUserByUsername(String username);
}
