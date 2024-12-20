package uz.jahonservice.loyiha.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jahonservice.loyiha.dto.ErrorDto;
import uz.jahonservice.loyiha.dto.response.ApiResponse;
import uz.jahonservice.loyiha.service.MainService;
import uz.jahonservice.loyiha.service.factorServices.Ferma;
import uz.jahonservice.loyiha.service.factorServices.PolardRho;
import uz.jahonservice.loyiha.service.factorServices.QuadraticSieve;
import uz.jahonservice.loyiha.service.validation.NumberValidation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final NumberValidation numberValidation;
    private final PolardRho polardRho;
    private final Ferma ferma;
    private final QuadraticSieve kvadratQoldiq;

    @Override
    public ApiResponse<List<BigInteger>> polard(String number) {

        List<ErrorDto> errorDto = numberValidation.validateNumber(number);

        if (numberValidation.isPrime(new BigInteger(number))) {
            errorDto.add(new ErrorDto("Prime number", number));
        }

        if (!errorDto.isEmpty()) {
            return ApiResponse.<List<BigInteger>>builder()
                    .code(-1)
                    .message("Xatolik sodir buldi, pastroqda natijasi bor ")
                    .success(false)
                    .errorList(errorDto)
                    .build();
        }

        long startTime = System.currentTimeMillis()-1;

        BigInteger n = new BigInteger(number);
        BigInteger number1 = polardRho.pollardsRho(n);
        List<BigInteger> numbers = new ArrayList<>();
        numbers.add(number1);
        numbers.add(n.divide(number1));
        long endTime = System.currentTimeMillis();

        return ApiResponse.<List<BigInteger>>builder()
                .code(0)
                .success(true)
                .message("Faktorizatsiyalash amalga oshirildi")
                .number(numbers)
                .time(endTime - startTime)
                .build();
    }

    @Override
    public ApiResponse<List<BigInteger>> ferma(String number) {
        List<ErrorDto> errorDto = numberValidation.validateNumber(number);

        if (numberValidation.isPrime(new BigInteger(number))) {
            errorDto.add(new ErrorDto("Prime number", number));
        }

        if (!errorDto.isEmpty()) {
            return ApiResponse.<List<BigInteger>>builder()
                    .code(-1)
                    .message("Xatolik sodir buldi, pastroqda natijasi bor")
                    .success(false)
                    .errorList(errorDto)
                    .build();
        }

        long startTime = System.currentTimeMillis() - 1;

        BigInteger n = new BigInteger(number);
        BigInteger x = ferma.sqrtCeil(n);
        BigInteger y;

        while (true) {
            BigInteger xSquared = x.pow(2);
            BigInteger ySquared = xSquared.subtract(n);
            if (ferma.isPerfectSquare(ySquared)) {
                y = ferma.sqrt(ySquared);
                BigInteger p = x.add(y);
                BigInteger q = x.subtract(y);
                List<BigInteger> numbers = new ArrayList<>();
                numbers.add(q);
                numbers.add(p);
                long endTime = System.currentTimeMillis();
                return ApiResponse.<List<BigInteger>>builder()
                        .code(0)
                        .success(true)
                        .message("Sonlar faktorizatsiyalandi")
                        .number(numbers)
                        .time(endTime - startTime)
                        .build();
            }
            x = x.add(BigInteger.ONE);
        }
    }

    @Override
    public ApiResponse<List<BigInteger>> kvadrat(String number) {
        List<ErrorDto> errorDto = numberValidation.validateNumber(number);

        if (numberValidation.isPrime(new BigInteger(number))) {
            errorDto.add(new ErrorDto("Prime number", number));
        }

        if (!errorDto.isEmpty()) {
            return ApiResponse.<List<BigInteger>>builder()
                    .code(-1)
                    .message("Xatolik sodir buldi, pastroqda natijasi bor ")
                    .success(false)
                    .errorList(errorDto)
                    .build();
        }

        long startTime = System.currentTimeMillis() - 1;

        BigInteger n = new BigInteger(number); // Faktorizatsiya qilinadigan son
        List<BigInteger> factors = kvadratQoldiq.factorize(n);

        long endTime = System.currentTimeMillis();
        return ApiResponse.<List<BigInteger>>builder()
                .code(0)
                .success(true)
                .message("Faktorizatsiyalash amalga oshi")
                .number(factors)
                .time(endTime - startTime)
                .build();
    }


}
