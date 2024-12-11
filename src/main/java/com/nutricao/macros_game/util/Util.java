package com.nutricao.macros_game.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {

    /**
     * Arredonda um número para 2 casas decimais utilizando BigDecimal.
     * @param valor O valor a ser arredondado.
     * @return O valor arredondado para 2 casas decimais.
     */
    public static Double arredondar(Double valor) {
        BigDecimal bigDecimal = new BigDecimal(valor);
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}