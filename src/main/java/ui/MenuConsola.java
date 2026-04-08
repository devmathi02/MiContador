package main.java.ui;

import main.java.gestion.Cuenta;
import main.java.modelo.Categoria;
import main.java.modelo.Gasto;
import main.java.modelo.Ingreso;
import main.java.modelo.Transaccion;

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
                case 5 -> editarTransaccion();   // NUEVO
                case 6 -> eliminarTransaccion(); // NUEVO
                case 7 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida");
            }
        }while (opcion != 7);
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n=== miContador ===");
        System.out.println("1. Registrar INGRESO");
        System.out.println("2. Registrar GASTO");
        System.out.println("3. Ver historial");
        System.out.println("4. Ver saldo actual");
        System.out.println("5. Editar transacción");   // NUEVO
        System.out.println("6. Eliminar transacción"); // NUEVO
        System.out.println("7. Salir");
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

    private void editarTransaccion() {
        var transacciones = cuenta.getTransacciones();

        if (transacciones.isEmpty()) {
            System.out.println("📭 No hay transacciones para editar.");
            return;
        }

        System.out.println("\n📜 === SELECCIONA UNA TRANSACCIÓN PARA EDITAR ===");
        for (int i = 0; i < transacciones.size(); i++) {
            System.out.println((i + 1) + ". " + transacciones.get(i));
        }

        System.out.print("\nNúmero de transacción a editar (0 para cancelar): ");
        int indice = leerEntero() - 1;

        if (indice < 0) {
            System.out.println("❌ Edición cancelada.");
            return;
        }

        Transaccion original = cuenta.getTransaccion(indice);
        if (original == null) {
            System.out.println("❌ Transacción no encontrada.");
            return;
        }

        System.out.println("\n📝 Editando: " + original);
        System.out.println("Deja en blanco para mantener el valor actual.\n");

        boolean esIngreso = original instanceof Ingreso;

        // Editar categoría
        System.out.println("Categoría actual: " + original.getCategoria().name());
        System.out.print("Nueva categoría (0 para mantener): ");
        String nuevaCategoriaStr = scanner.nextLine().toUpperCase();

        Categoria nuevaCategoria = original.getCategoria();
        if (!nuevaCategoriaStr.equals("0") && !nuevaCategoriaStr.isEmpty()) {
            try {
                nuevaCategoria = Categoria.valueOf(nuevaCategoriaStr);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Categoría inválida. Se mantiene la actual.");
            }
        }

        // Editar monto
        System.out.printf("Monto actual: $%.2f%n", original.getMonto());
        System.out.print("Nuevo monto (0 para mantener): ");
        double nuevoMonto = original.getMonto();
        String montoStr = scanner.nextLine();
        if (!montoStr.isEmpty() && !montoStr.equals("0")) {
            try {
                nuevoMonto = Double.parseDouble(montoStr);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Monto inválido. Se mantiene el actual.");
            }
        }

        // Editar descripción
        System.out.println("Descripción actual: " + original.getDescripcion());
        System.out.print("Nueva descripción (deja en blanco para mantener): ");
        String nuevaDescripcion = scanner.nextLine();
        if (nuevaDescripcion.isEmpty()) {
            nuevaDescripcion = original.getDescripcion();
        }

        // Crear nueva transacción
        Transaccion nuevaTransaccion;
        try {
            if (esIngreso) {
                nuevaTransaccion = new Ingreso(nuevoMonto, original.getFecha(), nuevaCategoria, nuevaDescripcion);
            } else {
                nuevaTransaccion = new Gasto(nuevoMonto, original.getFecha(), nuevaCategoria, nuevaDescripcion);
            }

            if (cuenta.editarTransaccion(indice, nuevaTransaccion)) {
                System.out.println("✅ Transacción editada correctamente");
            } else {
                System.out.println("❌ Error al editar");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error al editar: " + e.getMessage());
        }
    }

    private void eliminarTransaccion() {
        var transacciones = cuenta.getTransacciones();

        if (transacciones.isEmpty()) {
            System.out.println("📭 No hay transacciones para eliminar.");
            return;
        }

        System.out.println("\n📜 === SELECCIONA UNA TRANSACCIÓN PARA ELIMINAR ===");
        for (int i = 0; i < transacciones.size(); i++) {
            System.out.println((i + 1) + ". " + transacciones.get(i));
        }

        System.out.print("\nNúmero de transacción a eliminar (0 para cancelar): ");
        int indice = leerEntero() - 1;

        if (indice < 0) {
            System.out.println("❌ Eliminación cancelada.");
            return;
        }

        Transaccion eliminada = cuenta.getTransaccion(indice);
        if (eliminada == null) {
            System.out.println("❌ Transacción no encontrada.");
            return;
        }

        System.out.println("⚠️ ¿Estás seguro de eliminar?");
        System.out.println("   " + eliminada);
        System.out.print("   Confirma escribiendo 'SI': ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("SI")) {
            if (cuenta.eliminarTransaccion(indice)) {
                System.out.println("✅ Transacción eliminada correctamente");
            } else {
                System.out.println("❌ Error al eliminar");
            }
        } else {
            System.out.println("❌ Eliminación cancelada.");
        }
    }
}
