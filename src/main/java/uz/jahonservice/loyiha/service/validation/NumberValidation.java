package uz.jahonservice.loyiha.service.validation;

import org.springframework.stereotype.Component;
import uz.jahonservice.loyiha.dto.ErrorDto;

import java.math.BigInteger;
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


    public  boolean isPrime(BigInteger n) {
        // Agar son 2 dan kichik bo'lsa, tub emas
        if (n.compareTo(BigInteger.TWO) < 0) {
            return false;
        }

        // 2 va 3 uchun maxsus holatlar
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }

        // Agar son 2 ga yoki 3 ga bo'linsa, tub emas
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO) ||
            n.mod(BigInteger.valueOf(3)).equals(BigInteger.ZERO)) {
            return false;
        }

        // 5 va undan katta sonlar uchun tekshirish
        BigInteger i = BigInteger.valueOf(5);
        while (i.multiply(i).compareTo(n) <= 0) {
            if (n.mod(i).equals(BigInteger.ZERO) ||
                n.mod(i.add(BigInteger.TWO)).equals(BigInteger.ZERO)) {
                return false;
            }
            i = i.add(BigInteger.valueOf(6));
        }

        return true;
    }
}
