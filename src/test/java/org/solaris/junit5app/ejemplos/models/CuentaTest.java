package org.solaris.junit5app.ejemplos.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CuentaTest {

    @Test
    void testNombreCuenta() {

        Cuenta cuenta =new Cuenta("Juan Carlos", new BigDecimal("10000.12345"));
//        cuenta.setPersona("Juan Carlos");
        String esperado ="Juan Carlos";
        String real = cuenta.getPersona();
        Assertions.assertEquals(esperado,real);
        Assertions.assertTrue(real.equals("Juan Carlos"));
    }
    // Ctrl +shfit f10
    @Test
    void testSaldoCuenta() {
        Cuenta cuenta =new Cuenta("Juan Carlos", new BigDecimal("10000.12345"));
        Assertions.assertEquals(10000.12345,cuenta.getSaldo().doubleValue());
    }
}