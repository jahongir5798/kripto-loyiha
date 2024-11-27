package uz.jahonservice.loyiha.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse<T> {

    private int code;

    private String message;

    private boolean success;

    private String token;

    private T user;

}
