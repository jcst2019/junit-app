package org.solaris.junit5app.ejemplos.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.solaris.junit5app.ejemplos.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

class CuentaTest {

    @Test
    void testNombreCuenta() {

        Cuenta cuenta =new Cuenta("Juan Carlos", new BigDecimal("10000.12345"));
//        cuenta.setPersona("Juan Carlos");
        String esperado ="Juan Carlos";
        String real = cuenta.getPersona();
        Assertions.assertNotNull(real);
        Assertions.assertEquals(esperado,real);
        Assertions.assertTrue(real.equals("Juan Carlos"));
    }
    // Ctrl +shfit f10 => ejecutar el test
    @Test
    void testSaldoCuenta() {
        Cuenta cuenta =new Cuenta("Juan Carlos", new BigDecimal("10000.12345"));
        Assertions.assertNotNull(cuenta.getSaldo());
        Assertions.assertEquals(10000.12345,cuenta.getSaldo().doubleValue());
        Assertions.assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
        Assertions.assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta =new Cuenta("John Doe", new BigDecimal("999.12345"));
        Cuenta cuenta2 =new Cuenta("John Doe", new BigDecimal("999.12345"));
//        Assertions.assertNotEquals(cuenta2,cuenta);
        Assertions.assertEquals(cuenta2,cuenta);

    }
// Alt + insert (Inseter TEST)
    @Test
    void testDebitoCuenta() {
        Cuenta cuenta =new Cuenta("Juan Carlos", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        Assertions.assertNotNull(cuenta.getSaldo());
        Assertions.assertEquals(900,cuenta.getSaldo().intValue());
        Assertions.assertEquals("900.12345",cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta =new Cuenta("Juan Carlos", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        Assertions.assertNotNull(cuenta.getSaldo());
        Assertions.assertEquals(1100,cuenta.getSaldo().intValue());
        Assertions.assertEquals("1100.12345",cuenta.getSaldo().toPlainString());
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        Cuenta cuenta =new Cuenta("Juan Carlos", new BigDecimal("1000.12345"));
        Exception exception= Assertions.assertThrows(DineroInsuficienteException.class, ()->{
            cuenta.debito(new BigDecimal(1500));
        });
        String actual=exception.getMessage();
        String esperado="Dinero Insuficiente";
        Assertions.assertEquals(esperado,actual);
    }
}