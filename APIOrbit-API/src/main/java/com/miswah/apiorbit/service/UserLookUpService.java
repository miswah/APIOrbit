package com.miswah.apiorbit.service;

import com.miswah.apiorbit.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public interface UserLookUpService {
    UserModel getUserByEmail(String Email);
}
