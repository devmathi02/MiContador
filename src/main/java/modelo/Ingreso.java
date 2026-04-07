package main.java.modelo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Ingreso extends Transaccion{
    //lista valida de categorias para ingresos
    private static final List<Categoria> CATEGORIAS_VALIDAS = Arrays.asList(
            Categoria.SALARIO,
            Categoria.CACHUELO,
            Categoria.INVERSIONES,
            Categoria.OTROSINGRESOS
    );

    public Ingreso(double monto, Categoria categoria, String descripcion) {
        super(monto, categoria, descripcion);
        validarCategoria(categoria);
    }

    public Ingreso(double monto, LocalDate fecha, Categoria categoria, String descripcion) {
        super(monto, fecha, categoria, descripcion);
        validarCategoria(categoria);
    }

    private void validarCategoria(Categoria categoria){
        if (!CATEGORIAS_VALIDAS.contains(categoria)){
            throw new IllegalArgumentException(
                    "Categoria Invalida para Ingreso, ingrese una categoria valida"
            );
        }
    }

    @Override
    public String getTipo() {
        return "INGRESO";
    }

    @Override
    public double impactoEnSaldo() {
        return monto;
    }
}
