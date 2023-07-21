package pack.springjpa1.data.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class Token {
    private String token;
    private String refreshToken;

    @Builder
    public Token(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}