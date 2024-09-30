package Classes;

import java.util.Scanner;

import Classes.account.User;
import Menu.main.Index;

public class Main {
    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
        //Instancias de prueba
        User admin = new User("José", "jfnr398", "1234");
        User user = new User("Fernando", "fercho398", "4321");
        admin.getTransactionManager().createTransaction(1, "Ingreso", 6500, "pagoNomina", user.getCatogories().selectCategory(1));
        admin.getTransactionManager().createTransaction(1, "Gasto", 1400, "pagoArriendo", user.getCatogories().selectCategory(1));
        Index.main();
    }
}