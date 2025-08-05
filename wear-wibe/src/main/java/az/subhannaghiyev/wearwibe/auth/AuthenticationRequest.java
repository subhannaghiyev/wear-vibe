package az.subhannaghiyev.wearwibe.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;

}
