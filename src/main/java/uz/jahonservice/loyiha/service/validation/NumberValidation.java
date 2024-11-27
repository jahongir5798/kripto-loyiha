package uz.jahonservice.loyiha.service.validation;

import org.springframework.stereotype.Component;
import uz.jahonservice.loyiha.dto.ErrorDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class NumberValidation {
    public List<ErrorDto> validateNumber(String number) {
        String rejax = "[0-9]+";
        List<ErrorDto> errors = new ArrayList<>();
        if (!number.matches(rejax)) {
            errors.add(new ErrorDto("ortiqcha belgilar mavjud", "number"));
        }
        return errors;
    }
}
