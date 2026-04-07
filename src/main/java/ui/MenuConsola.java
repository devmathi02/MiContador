package main.java.ui;

import main.java.gestion.Cuenta;
import main.java.modelo.Categoria;
import main.java.modelo.Gasto;
import main.java.modelo.Ingreso;

import java.util.Scanner;

public class MenuConsola {
    private Cuenta cuenta;
    private Scanner scanner;

    public MenuConsola(){
        this.cuenta = new Cuenta();
        this.scanner = new Scanner(System.in);
    }

    public void Iniciar(){
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero();

            switch (opcion){
                case 1 -> registrarIngreso();
                case 2 -> registrarGasto();
                case 3 -> mostrarHistorial();
                case 4 -> mostrarSaldo();
                case 5 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida");
            }
        }while (opcion != 5);
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n=== miContador ===");
        System.out.println("1. Registrar INGRESO");
        System.out.println("2. Registrar GASTO");
        System.out.println("3. Ver historial");
        System.out.println("4. Ver saldo actual");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");
    }

    private void registrarIngreso(){
        System.out.println("\n--- NUEVO INGRESO ---");

        System.out.println("Categorías disponibles:");
        System.out.println("  - SALARIO");
        System.out.println("  - CACHUELOS");
        System.out.println("  - INVERSIONES");
        System.out.println("  - OTROSINGRESOS");
        System.out.print("Categoría: ");

        String catNombre = scanner.nextLine().toUpperCase();

        System.out.print("Monto: $");
        double monto = leerDouble();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        try {
            Categoria categoria = Categoria.valueOf(catNombre);
            Ingreso ingreso = new Ingreso(monto, categoria, descripcion);
            cuenta.agregarTransaccion(ingreso);
            System.out.println("Ingreso registrado!!!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registrarGasto() {
        System.out.println("\n--- NUEVO GASTO ---");

        System.out.println("Categorías disponibles:");
        System.out.println("  - COMIDA");
        System.out.println("  - TRANSPORTE");
        System.out.println("  - HOGAR");
        System.out.println("  - SERVICIOS");
        System.out.println("  - SALUD");
        System.out.println("  - SALIDAS");
        System.out.println("  - EDUCACION");
        System.out.println("  - ROPAYZAPATOS");
        System.out.println("  - OTROSGASTOS");

        System.out.print("Categoría: ");
        String catNombre = scanner.nextLine().toUpperCase();

        System.out.print("Monto: $");
        double monto = leerDouble();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        try {
            Categoria categoria = Categoria.valueOf(catNombre);
            Gasto gasto = new Gasto(monto, categoria, descripcion);
            cuenta.agregarTransaccion(gasto);
            System.out.println("Gasto registrado!!!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarHistorial() {
        var transacciones = cuenta.getTransacciones();

        if (transacciones.isEmpty()) {
            System.out.println("📭 No hay transacciones registradas.");
            return;
        }

        System.out.println("\n📜 === HISTORIAL ===");
        for (int i = 0; i < transacciones.size(); i++) {
            System.out.println((i + 1) + ". " + transacciones.get(i));
        }
    }

    private void mostrarSaldo() {
        System.out.printf("\n💰 Saldo actual: $%.2f%n", cuenta.getSaldoActual());
    }

    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingresa un número: ");
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Por favor, ingresa un número válido: ");
            scanner.next();
        }
        double numero = scanner.nextDouble();
        scanner.nextLine();
        return numero;
    }


}
