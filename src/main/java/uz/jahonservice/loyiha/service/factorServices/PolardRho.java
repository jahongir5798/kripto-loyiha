package uz.jahonservice.loyiha.service.factorServices;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Random;

@Component
public class PolardRho {

    //todo: Faktorizatsiyalash uchun polard algoritmi
    public  BigInteger pollardsRho(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return BigInteger.TWO; // Even number, divisible by 2
        }

        BigInteger x = BigInteger.valueOf(2);
        BigInteger y = BigInteger.valueOf(2);
        BigInteger d = BigInteger.ONE;
        BigInteger c = BigInteger.ONE; // funksiyaning boshlangich qiymatlari
        Random rand = new Random();

        while (d.equals(BigInteger.ONE)) {
            // x = (x^2 + c) % n
            x = x.multiply(x).add(c).mod(n);

            // y = (y^2 + c) % n formula buyicha ikki marotaba qullaniladi
            y = y.multiply(y).add(c).mod(n);
            y = y.multiply(y).add(c).mod(n);

            // d = gcd(|x - y|, n)
            d = x.subtract(y).abs().gcd(n);


            if (d.equals(n)) {
                c = BigInteger.valueOf(rand.nextInt(100) + 3); // 3 urnida ixtoyoriy tub son bulishi mumkin
                x = BigInteger.valueOf(2);
                y = BigInteger.valueOf(2);
                d = BigInteger.ONE;
            }
        }
        return d;
    }
}
