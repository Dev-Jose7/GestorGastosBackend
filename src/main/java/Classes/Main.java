package Classes;

import java.util.Scanner;

import Classes.account.User;
import Menu.main.Index;

public class Main {
    public static Scanner lector = new Scanner(System.in);

    public static void main(String[] args) {
        //Instancias de prueba

        Test.instanceUsers(); //Usuarios
        Test.instanceTransactions(); //Transacciones de los usuarios

        Index.main();
    }
}