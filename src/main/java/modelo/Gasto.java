package main.java.modelo;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Gasto extends Transaccion{
    //lista valida de categorias para gastos
    private static final List<Categoria> CATEGORIAS_VALIDAS = Arrays.asList(
            Categoria.COMIDA,
            Categoria.TRANSPORTE,
            Categoria.HOGAR,
            Categoria.SERVICIOS,
            Categoria.SALUD,
            Categoria.SALIDAS,
            Categoria.EDUCACION,
            Categoria.ROPAYZAPATOS,
            Categoria.OTROSGASTOS
    );

    public Gasto(double monto, Categoria categoria, String descripcion){
        super(monto, categoria, descripcion);
        validarCategoria(categoria);
    }

    public Gasto(double monto, LocalDate fecha, Categoria categoria, String descripcion) {
        super(monto, fecha, categoria, descripcion);
        validarCategoria(categoria);
    }

    private void validarCategoria(Categoria categoria){
        if (!CATEGORIAS_VALIDAS.contains(categoria)){
            throw new IllegalArgumentException(
                    "Categoria Invalida para Gasto, ingrese una categoria valida"
            );
        }
    }

    @Override
    public String getTipo() {
        return "GASTO";
    }

    @Override
    public double impactoEnSaldo() {
        return -monto;
    }
}
