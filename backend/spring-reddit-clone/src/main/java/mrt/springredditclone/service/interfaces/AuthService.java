package mrt.springredditclone.service.interfaces;

import mrt.springredditclone.dto.AuthenticationResponse;
import mrt.springredditclone.dto.LoginRequest;
import mrt.springredditclone.dto.RefreshTokenRequest;
import mrt.springredditclone.dto.RegisterRequest;
import mrt.springredditclone.model.User;

public interface AuthService {

    void signup(RegisterRequest registerRequest);

    void verifyAccount(String token);

    AuthenticationResponse login(LoginRequest loginRequest);

    User getCurrentUser ();

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
