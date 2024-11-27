package uz.jahonservice.loyiha.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDto {
    private String message;

    private String field;
}
