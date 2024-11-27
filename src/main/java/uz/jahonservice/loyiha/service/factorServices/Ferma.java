package uz.jahonservice.loyiha.service.factorServices;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class Ferma {
     //todo: Ferma testi uchun faktorizatsiyalash muammosida qullaniladigan methodlar
    // Kvadrat ildizni yuqoriga qarab yaxlitlash
    public  BigInteger sqrtCeil(BigInteger n) {
        BigInteger sqrt = sqrt(n);
        if (sqrt.pow(2).compareTo(n) < 0) {
            sqrt = sqrt.add(BigInteger.ONE);
        }
        return sqrt;
    }

    // Kvadrat ildiz
    public  BigInteger sqrt(BigInteger n) {
        BigInteger x = n;
        BigInteger y = x.add(BigInteger.ONE).divide(BigInteger.TWO);
        while (y.compareTo(x) < 0) {
            x = y;
            y = x.add(n.divide(x)).divide(BigInteger.TWO);
        }
        return x;
    }

    // Kvadrat tekshirish
    public  boolean isPerfectSquare(BigInteger n) {
        BigInteger sqrt = sqrt(n);
        return sqrt.pow(2).equals(n);
    }
}
