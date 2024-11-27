package uz.jahonservice.loyiha.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jahonservice.loyiha.dto.response.ApiResponse;
import uz.jahonservice.loyiha.service.MainService;

import java.math.BigInteger;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("api/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;


    @GetMapping("/polard-rho")
    public ApiResponse<List<BigInteger>> polard(String number){
        log.info("main controller polard method invoked");
        ApiResponse<List<BigInteger>> polard = mainService.polard(number);
        log.info("main controller polard method completed");
        return polard;
    }

    @GetMapping("/ferma")
    public ApiResponse<List<BigInteger>> ferma(String number){
        log.info("main controller ferma method invoked");
        ApiResponse<List<BigInteger>> ferma = mainService.ferma(number);
        log.info("main controller ferma method completed");
        return ferma;
    }

    @GetMapping("/kvadrat-qoldiq")
    public ApiResponse<List<BigInteger>> kvadratQoldiq(String number){
        log.info("main controller kvadrat-qoldiq method invoked");
        ApiResponse<List<BigInteger>> kvadrat = mainService.kvadrat(number);
        log.info("main controller kvadrat-qoldiq method completed");
        return kvadrat;
    }


    
}
