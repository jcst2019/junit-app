package org.solaris.junit5app.ejemplos.models;

import org.junit.jupiter.api.*;
import org.solaris.junit5app.ejemplos.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    Cuenta cuenta;

    @BeforeEach
    void initMetodoTest(){
        this.cuenta = new Cuenta("Juan Carlos", new BigDecimal("1000.12345"));
        System.out.println("Iniciando el método...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando el metodo de prueba.");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando el Test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando el Test");
    }

    @Test
    @DisplayName("Probando el nombre de la Cuenta Corriente")
    void testNombreCuenta() {

//        cuenta.setPersona("Juan Carlos");
        String esperado = "Juan Carlos";
        String real = cuenta.getPersona();
        Assertions.assertNotNull(real,()->"La cuenta No mpuede ser Nula");
        Assertions.assertEquals(esperado, real, ()->"El nombre de la cuenta no es la que se esperaba");
        Assertions.assertTrue(real.equals("Juan Carlos"), ()->"Nombre de la cuenta esperada debe ser igual al real");
    }

    // Ctrl +shfit f10 => ejecutar el test
    @Test
    @DisplayName("Probando el saldo de la cuenta corriente, que nosa null, mayor esperado , valor esperado")
    void testSaldoCuenta() {
//        cuenta = new Cuenta("Juan Carlos", new BigDecimal("1000.12345"));
        Assertions.assertNotNull(cuenta.getSaldo());
        Assertions.assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        Assertions.assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
        Assertions.assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    @DisplayName("testeando referencias que sean iguales  con el metodo equals")
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("999.12345"));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("999.12345"));
//        Assertions.assertNotEquals(cuenta2,cuenta);
        Assertions.assertEquals(cuenta2, cuenta);

    }

    // Alt + insert (Inseter TEST)
    @Test
    void testDebitoCuenta() {

        cuenta.debito(new BigDecimal(100));
        Assertions.assertNotNull(cuenta.getSaldo());
        Assertions.assertEquals(900, cuenta.getSaldo().intValue());
        Assertions.assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Juan Carlos", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        Assertions.assertNotNull(cuenta.getSaldo());
        Assertions.assertEquals(1100, cuenta.getSaldo().intValue());
        Assertions.assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        Cuenta cuenta = new Cuenta("Juan Carlos", new BigDecimal("1000.12345"));
        Exception exception = Assertions.assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    void testTranferirDineroCuentas() {
        Cuenta cuenta1 = new Cuenta("Juan Carlos", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));
        Banco banco = new Banco();
        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal("500"));
        Assertions.assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        Assertions.assertEquals("3000", cuenta1.getSaldo().toPlainString());
    }
// CTRL + ALT + L ORDENA EL CODIGO
//    @Test
//    void testRelacionBancoCuentas() {
//        Cuenta cuenta1 = new Cuenta("Juan Carlos", new BigDecimal("2500"));
//        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));
//        Banco banco = new Banco();
//        banco.addCuenta(cuenta1);
//        banco.addCuenta(cuenta2);
//        banco.setNombre("Banco del Estado");
//        banco.transferir(cuenta2, cuenta1, new BigDecimal("500"));
//        // Muestra todos los errores que se generen
////        Assertions.assertAll(() -> {
////
////        });
//        Assertions.assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
//        Assertions.assertEquals("3000", cuenta1.getSaldo().toPlainString());
//
//        Assertions.assertEquals(2, banco.getCuentas().size());
//        Assertions.assertEquals("Banco del Estado", cuenta1.getBanco().getNombre());
//        Assertions.assertEquals("Andres", banco.getCuentas().stream()
//                .filter(c -> c.getPersona().equals("Andres"))
//                .findFirst()
//                .get().getPersona()
//        );
////        Assertions.assertTrue(banco.getCuentas().stream()
////                .filter(c ->c.getPersona().equals("Andres"))
////                .findFirst()
////                .isPresent()
////        );
//        Assertions.assertTrue(banco.getCuentas().stream()
//                .anyMatch(c -> c.getPersona().equals("Andres"))
//        );
//    }
    @Test
    @Disabled
    @DisplayName("Probando relaciones  entre las cuentas y el banco con AssertAll")
    void testRelacionBancoCuentas() {
//        fail(); // Forzar el error
        Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);

        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        assertAll(() -> Assertions.assertEquals("1000.8989", cuenta2.getSaldo().toPlainString(),
                        () -> "el valor del saldo de la cuenta2 no es el esperado."),
                () -> Assertions.assertEquals("3000", cuenta1.getSaldo().toPlainString(),
                        () -> "el valor del saldo de la cuenta1 no es el esperado."),
                () -> Assertions.assertEquals(2, banco.getCuentas().size(), () -> "el banco no tienes las cuentas esperadas"),
                () -> Assertions.assertEquals("Banco del Estado", cuenta1.getBanco().getNombre()),
                () -> assertEquals("Andres", banco.getCuentas().stream()
                        .filter(c -> c.getPersona().equals("Andres"))
                        .findFirst()
                        .get().getPersona()),
                () -> Assertions.assertTrue(banco.getCuentas().stream()
                        .anyMatch(c -> c.getPersona().equals("Jhon Doe"))));
    }
}