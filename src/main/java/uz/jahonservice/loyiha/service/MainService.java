package uz.jahonservice.loyiha.service;

import uz.jahonservice.loyiha.dto.response.ApiResponse;

import java.math.BigInteger;
import java.util.List;

public interface MainService {
    ApiResponse<List<BigInteger>> polard(String number);

    ApiResponse<List<BigInteger>> ferma(String number);

    ApiResponse<List<BigInteger>> kvadrat(String number);
}
