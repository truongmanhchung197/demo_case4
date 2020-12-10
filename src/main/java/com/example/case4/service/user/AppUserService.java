package com.example.case4.service.user;

import com.example.case4.model.AppUser;
import com.example.case4.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService implements IAppUserService, UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public void remove(Long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public AppUser getUserByUsername(String name) {
        return appUserRepository.getAppUserByUsername(name);
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        AppUser appUser = this.getUserByUsername(name);
        return appUser.getId();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = this.getUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(appUser.getRole());
        UserDetails userDetails = new User(
                appUser.getUsername(),
                appUser.getPassword() ,
                authorities);

        return userDetails;
    }
}
