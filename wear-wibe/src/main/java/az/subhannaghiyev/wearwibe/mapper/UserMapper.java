package az.subhannaghiyev.wearwibe.mapper;

import az.subhannaghiyev.wearwibe.dto.*;
import az.subhannaghiyev.wearwibe.entity.*;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User user);

    User toEntity(UserResponseDto userDto);

    List<UserResponseDto> toDtoList(List<User> users);
    
}