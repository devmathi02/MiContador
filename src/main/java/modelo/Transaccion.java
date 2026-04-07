package main.java.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Transaccion {
    protected double monto;
    protected LocalDate fecha;
    protected Categoria categoria;
    protected String descripcion;

    //constructor con fecha actual
    public Transaccion(double monto, Categoria categoria, String descripcion) {
        this.monto = monto;
        this.fecha = LocalDate.now();
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    //constructor con fecha especifica a la hora de cargar el archivo
    public Transaccion(double monto, LocalDate fecha, Categoria categoria, String descripcion) {
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    //getters
    public double getMonto() {return monto;}
    public LocalDate getFecha() {return fecha;}
    public Categoria getCategoria() {return categoria;}
    public String getDescripcion() {return descripcion;}

    //metodos que heredan las clases hijas
    public abstract String getTipo(); // ingreso o gasto
    public abstract double impactoEnSaldo();// positivo para ingresos, negativo para gastos

    //formato de consola
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[%s] %s | %s | %s: $%.2f",
                getTipo(),
                fecha.format(formatter),
                categoria.getEmoji(),
                descripcion,
                monto);
    }
}
