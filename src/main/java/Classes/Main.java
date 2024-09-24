package Classes;

import java.util.Scanner;
import Menu.Index;

public class Main {
    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
        //Instancias de prueba
        User admin = new User("José", "jfnr398", "1234");
        User user = new User("Fernando" ,"fercho398", "4321");
        admin.getTransactions().createTransaction(1, "Ingreso", 6500, "pagoNomina", user.getCatogories().selectCategory(1));
        admin.getTransactions().createTransaction(1, "Gasto", 1400, "pagoArriendo", user.getCatogories().selectCategory(2));
        Index.main();
    }
}