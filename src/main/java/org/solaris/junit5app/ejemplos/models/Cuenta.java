package org.solaris.junit5app.ejemplos.models;

import org.solaris.junit5app.ejemplos.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

public class Cuenta {

    private String persona;
    private BigDecimal saldo;

    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void debito(BigDecimal monto){
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO)<0){
            throw new DineroInsuficienteException("Dinero Insuficiente");
        }else{
            this.saldo=nuevoSaldo;
        }
    }

    public void credito(BigDecimal monto){
        //BigDecimal => es inmutable po lo que el resultado tiene que asignarse a this.saldo
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public boolean equals(Object obj) {
//        if (obj == null)
//            return false;
        if (!(obj instanceof Cuenta))
            return false;
        Cuenta c = (Cuenta)obj;

        if(this.persona==null||this.saldo==null)
            return false;
        return this.persona.equals(c.getPersona())&&this.saldo.equals(c.getSaldo());
    }
}
