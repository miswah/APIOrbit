package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.model.UserModel;
import com.miswah.apiorbit.repository.UserRepository;
import com.miswah.apiorbit.service.UserLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLookUpServiceImp implements UserLookUpService {
    private final UserRepository userRepository;

    @Autowired
    public UserLookUpServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow();
    }
}
