package pack.springjpa1.common.security;

import lombok.*;

@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class TokenDto {
    private String access_token;
    private String refresh_token;
}
