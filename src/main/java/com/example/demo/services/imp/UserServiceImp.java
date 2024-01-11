package com.example.demo.services.imp;

import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entities.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserResponse getById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new NotFoundException("User doesn't exist!", HttpStatus.BAD_REQUEST);
        return userMapper.toDto(user.get());
    }

    @Override
    public void register(UserRequest userRequest) {
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());
        user.setCourse(userRequest.getCourse());

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new NotFoundException("User doesn't exist!", HttpStatus.BAD_REQUEST);
        else userRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, UserRequest userRequest){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new NotFoundException("User doesn't exist!", HttpStatus.BAD_REQUEST);
        user.get().setAge(userRequest.getAge());
        user.get().setName(userRequest.getName());
        user.get().setCourse(userRequest.getCourse());
        userRepository.save(user.get());
    }
}
