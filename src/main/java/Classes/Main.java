package Classes;

import java.util.Scanner;

import Classes.account.User;
import Menu.main.Index;

public class Main {
    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
        //Instancias de prueba

        //Usuarios
        User admin = new User("José", "jfnr398", "1234");
        User user1 = new User("Fernando", "fercho398", "4321");
        User user2 = new User("María", "maria123", "password1");
        User user3 = new User("Carlos", "carlos456", "pass456");
        User user4 = new User("Ana", "ana789", "ana789");
        User user5 = new User("Luis", "luis321", "luispass");

        //Transacciones de los usuarios
        //Admin
        admin.getTransactionManager().createTransaction(admin.getId(), "Ingreso", 6500, "pagoNomina", admin.getCatogories().selectCategory(1));
        admin.getTransactionManager().createTransaction(admin.getId(), "Gasto", 1400, "pagoArriendo", admin.getCatogories().selectCategory(2));
        admin.getTransactionManager().createTransaction(admin.getId(), "Ingreso", 3000, "freelanceProject", "Ingreso");
        admin.getTransactionManager().createTransaction(admin.getId(), "Gasto", 800, "pagoServicios", "Servicios");
        admin.getTransactionManager().createTransaction(admin.getId(), "Gasto", 300, "transporteTrabajo", "Transporte");

        //user1
        user1.getTransactionManager().createTransaction(user1.getId(), "Ingreso", 7000, "pagoSueldo", "Salario");
        user1.getTransactionManager().createTransaction(user1.getId(), "Gasto", 2000, "arriendoCasa", "Arriendo");
        user1.getTransactionManager().createTransaction(user1.getId(), "Ingreso", 4000, "ventaOnline", "Ingreso");
        user1.getTransactionManager().createTransaction(user1.getId(), "Gasto", 500, "pagoServicios", "Servicios");
        user1.getTransactionManager().createTransaction(user1.getId(), "Gasto", 100, "taxi", "Transporte");

        //user2
        user2.getTransactionManager().createTransaction(user2.getId(), "Ingreso", 9000, "salarioMensual", "Salario");
        user2.getTransactionManager().createTransaction(user2.getId(), "Gasto", 1800, "alquiler", "Arriendo");
        user2.getTransactionManager().createTransaction(user2.getId(), "Ingreso", 2500, "proyectoFreelance", "Ingreso");
        user2.getTransactionManager().createTransaction(user2.getId(), "Gasto", 700, "pagoElectricidad", "Servicios");
        user2.getTransactionManager().createTransaction(user2.getId(), "Gasto", 250, "bus", "Transporte");

        //user3
        user3.getTransactionManager().createTransaction(user3.getId(), "Ingreso", 8000, "salarioQuincenal", "Salario");
        user3.getTransactionManager().createTransaction(user3.getId(), "Gasto", 1200, "pagoAlquiler", "Arriendo");
        user3.getTransactionManager().createTransaction(user3.getId(), "Ingreso", 3500, "comisiónVenta", "Ingreso");
        user3.getTransactionManager().createTransaction(user3.getId(), "Gasto", 600, "facturaAgua", "Servicios");
        user3.getTransactionManager().createTransaction(user3.getId(), "Gasto", 400, "taxi", "Transporte");

        //user4
        user4.getTransactionManager().createTransaction(user4.getId(), "Ingreso", 9500, "sueldoMensual", "Salario");
        user4.getTransactionManager().createTransaction(user4.getId(), "Gasto", 2000, "arriendoApartamento", "Arriendo");
        user4.getTransactionManager().createTransaction(user4.getId(), "Ingreso", 5000, "ventaProducto", "Ingreso");
        user4.getTransactionManager().createTransaction(user4.getId(), "Gasto", 850, "pagoServicios", "Servicios");
        user4.getTransactionManager().createTransaction(user4.getId(), "Gasto", 150, "pasajeBus", "Transporte");

        //user5
        user5.getTransactionManager().createTransaction(user5.getId(), "Ingreso", 4000, "freelance", "Salario");
        user5.getTransactionManager().createTransaction(user5.getId(), "Ingreso", 1500, "venta de productos", "Ingreso");
        user5.getTransactionManager().createTransaction(user5.getId(), "Gasto", 800, "renta mensual", "Arriendo");
        user5.getTransactionManager().createTransaction(user5.getId(), "Gasto", 100, "electricidad", "Servicios");
        user5.getTransactionManager().createTransaction(user5.getId(), "Gasto", 50, "transporte público", "Transporte");

        Index.main();
    }
}