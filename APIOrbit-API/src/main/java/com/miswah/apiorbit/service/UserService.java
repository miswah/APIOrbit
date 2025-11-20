package com.miswah.apiorbit.service;

import com.miswah.apiorbit.dto.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {

    public List<UserResponseDTO> getAllUsers();
    public UserResponseDTO approve(UUID uuid);
    public UserResponseDTO disable(UUID uuid);
}
