package com.example.demo.services.imp;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entities.User;
import com.example.demo.exception.NotFoundException;
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
    @Override
    public UserResponse getById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println("User does not exist!");
            return null;
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.get().getId());
        userResponse.setName(user.get().getName());
        userResponse.setCourse(user.get().getCourse());
        userResponse.setAge(user.get().getAge());
        return userResponse;
    }

    @Override
    public void register(UserRequest userRequest) {
        if (userRequest.getName().contains("@"))
            throw new NotFoundException("ff", HttpStatus.BAD_GATEWAY);
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
            throw new NotFoundException("12345", HttpStatus.BAD_REQUEST);
        else userRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, UserRequest userRequest){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            System.out.println("User doesn't exist");
        else{
            user.get().setAge(userRequest.getAge());
            user.get().setName(userRequest.getName());
            user.get().setCourse(userRequest.getCourse());
            userRepository.save(user.get());
        }
    }
}
