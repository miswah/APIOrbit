package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.response.UserResponseDTO;
import com.miswah.apiorbit.enums.UserStatus;
import com.miswah.apiorbit.model.UserModel;
import com.miswah.apiorbit.repository.UserRepository;
import com.miswah.apiorbit.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserModel> model = this.userRepository.findAll(); //TODO: Pagination on for userlist

        List<UserResponseDTO> dto = new ArrayList<>();

        //TODO: Something better in case when there is no user
        if(model.isEmpty()){
            return dto;
        }

        return convertToDTOList(model);
    }

    @Override
    public UserResponseDTO approve(UUID uuid) {
        UserModel model = this.userRepository.findById(uuid).orElseThrow();

        model.setStatus(UserStatus.ACTIVE);

        this.userRepository.save(model);

        return this.convertToDTO(model);
    }

    @Override
    public UserResponseDTO disable(UUID uuid) {
        UserModel model = this.userRepository.findById(uuid).orElseThrow();

        model.setStatus(UserStatus.DEACTIVE);

        this.userRepository.save(model);

        return this.convertToDTO(model);
    }

    @Override
    public UserResponseDTO getUser(UUID uuid) {
        UserModel model = this.userRepository.findById(uuid).orElseThrow();
        return this.convertToDTO(model);
    }


    private UserResponseDTO convertToDTO(UserModel model){
      return new UserResponseDTO(model.getId(), model.getEmail(), model.getName(), model.getRole());
    }

    private List<UserResponseDTO> convertToDTOList(List<UserModel> models){
        return models.parallelStream().map(this::convertToDTO).toList();
    }
}
