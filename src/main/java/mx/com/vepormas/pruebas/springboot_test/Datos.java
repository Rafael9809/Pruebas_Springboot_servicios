
package mx.com.vepormas.pruebas.springboot_test;

import java.math.BigDecimal;

import mx.com.vepormas.pruebas.springboot_test.modelos.Banco;
import mx.com.vepormas.pruebas.springboot_test.modelos.Cuenta;

public class Datos {
    public static final Cuenta c1 = new Cuenta(1L, "Rafael", new BigDecimal("1000"));
    public static final Cuenta c2 = new Cuenta(2L, "Andres", new BigDecimal("2000"));
    public static final Banco banco =  new Banco(1L, "Banco", 0);

    public static Cuenta crearC1(){
        return new Cuenta(1L, "Rafael", new BigDecimal("1000"));
    }
    public static Cuenta crearC2(){
        return new Cuenta(1L, "Andres", new BigDecimal("2000"));
    }
}
