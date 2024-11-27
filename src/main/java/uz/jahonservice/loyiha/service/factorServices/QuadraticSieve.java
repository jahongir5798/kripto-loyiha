package uz.jahonservice.loyiha.service.factorServices;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuadraticSieve {


    //todo: Kvadrat qoldiqlar yordamida faktorizatsiya qilish
    public  List<BigInteger> factorize(BigInteger n) {
        List<BigInteger> factors = new ArrayList<>();

        // 2 ga bo'linishini tekshirish
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            factors.add(BigInteger.TWO);
            n = n.divide(BigInteger.TWO);
        }

        // 3 dan boshlanib, sqrt(n) gacha bo'lishni tekshirish
        BigInteger sqrtN = sqrt(n);
        for (BigInteger i = BigInteger.valueOf(3);
             i.compareTo(sqrtN) <= 0;
             i = i.add(BigInteger.TWO)) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                n = n.divide(i);
            }
            if (n.equals(BigInteger.ONE)) {
                break;
            }
        }

        // Qolgan n tub bo'lsa, uni qo'shamiz
        if (!n.equals(BigInteger.ONE)) {
            factors.add(n);
        }

        return factors;
    }

    // Kvadrat ildiz hisoblash
    public  BigInteger sqrt(BigInteger x) {
        BigInteger left = BigInteger.ONE;
        BigInteger right = x;
        while (left.compareTo(right) <= 0) {
            BigInteger mid = left.add(right).shiftRight(1);
            BigInteger midSquared = mid.multiply(mid);
            if (midSquared.equals(x)) {
                return mid;
            } else if (midSquared.compareTo(x) < 0) {
                left = mid.add(BigInteger.ONE);
            } else {
                right = mid.subtract(BigInteger.ONE);
            }
        }
        return right;
    }
}
