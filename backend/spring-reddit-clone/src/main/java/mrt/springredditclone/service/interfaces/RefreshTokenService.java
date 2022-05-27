package mrt.springredditclone.service.interfaces;

import mrt.springredditclone.model.RefreshToken;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}
