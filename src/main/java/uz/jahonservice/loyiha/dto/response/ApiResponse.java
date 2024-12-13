package uz.jahonservice.loyiha.dto.response;
import lombok.*;
import uz.jahonservice.loyiha.dto.ErrorDto;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private int code;

    private String message;

    private boolean success;

    private T number;

    private long time;

    private List<ErrorDto> errorList;
}
