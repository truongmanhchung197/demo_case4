package com.example.case4.service.user;

import com.example.case4.model.AppUser;
import com.example.case4.service.IService;

public interface IAppUserService extends IService<AppUser> {
    AppUser getUserByUsername(String name);
    Long getCurrentUserId();
}
