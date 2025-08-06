package az.subhannaghiyev.wearwibe.service;

import az.subhannaghiyev.wearwibe.dto.UserResponseDto;
import az.subhannaghiyev.wearwibe.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDto saveUser(User user);

    Optional<UserResponseDto> getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long id, User userDetails);

    void deleteUser(Long id);
}
