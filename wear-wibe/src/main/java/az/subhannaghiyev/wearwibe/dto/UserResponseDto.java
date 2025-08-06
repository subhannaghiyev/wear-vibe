package az.subhannaghiyev.wearwibe.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String mobileNumber;
    private LocalDate dateOfBirth;

}
