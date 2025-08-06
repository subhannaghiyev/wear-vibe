package az.subhannaghiyev.wearwibe.service.impl;

import az.subhannaghiyev.wearwibe.dto.UserResponseDto;
import az.subhannaghiyev.wearwibe.entity.User;
import az.subhannaghiyev.wearwibe.mapper.UserMapper;
import az.subhannaghiyev.wearwibe.repository.UserRepository;
import az.subhannaghiyev.wearwibe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto saveUser(User userRequestDto) {
        User user = userRepository.save(userRequestDto);
        return userMapper.toDto(user);
    }

    @Override
    public Optional<UserResponseDto> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDto);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    public UserResponseDto updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setEmail(userDetails.getEmail());
        user.setMobileNumber(userDetails.getMobileNumber());
        user.setPassword(userDetails.getPassword());
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setActive(false);
        userRepository.save(user);
    }
}
