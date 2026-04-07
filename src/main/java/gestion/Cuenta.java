package main.java.gestion;

import main.java.modelo.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private List<Transaccion> transacciones;
    private double saldoInicial;

    //constructor con saldo inical 0
    public Cuenta(){
        this(0.0);
    }

    //constructor con saldo personalizado
    public Cuenta(double saldoInicial) {
        this.transacciones = new ArrayList<>();
        this.saldoInicial = saldoInicial;
    }

    //agregar transaccion
    public void agregarTransaccion(Transaccion transaccion){
        transacciones.add(transaccion);
    }

    //calcula el saldo actual sumando las transacciones
    public double getSaldoActual(){
        double totalImpacto = transacciones.stream()
                .mapToDouble(Transaccion::impactoEnSaldo)
                .sum();
        return saldoInicial + totalImpacto;
    }

    //backup de la lista
    public List<Transaccion> getTransacciones(){
        return new ArrayList<>(transacciones);
    }

    //transacciones registradas
    public int getCantidadTransacciones(){
        return transacciones.size();
    }


}
