package Menu.panel.user;

import Classes.account.User;
import Classes.operation.Transaction;
import Menu.panel.Dashboard;

import static Classes.Main.lector;

public class Admin {
    public static void dashboard(User user){
        System.out.println("");
        System.out.println("======MONEY MANAGER======");
        System.out.println("=====DASHBOARD ADMIN=====");
        System.out.println("===========MENU==========");
        System.out.println("1. Consultar usuarios");
        System.out.println("2. Consultar transacciones");
        System.out.println("3. Dashboard principal");
        int option = lector.nextInt();

        switch (option){
            case 1:
                usersManager(user);
                break;

            case 2:
                transactionsManager(user);
                break;

            case 3:
                Dashboard.menu(user);
                break;
        }
    }

    public static void usersManager(User user){
        user.printData();
        System.out.println("Total de usuarios creados: " + User.totalUsers());
        int option;

        do{
            System.out.println("Digite el número de la opción");
            System.out.println("1. Dashboard Admin");
            option = lector.nextInt();

            switch (option){
                case 1:
                    dashboard(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while(option > 1);
    }

    public static void transactionsManager(User user){
        user.getTransactions().printData();
        System.out.println("Total de transacciones creadas: " + Transaction.totalTransactions());
        int option;

        do{
            System.out.println("Digite el número de la opción");
            System.out.println("1. Dashboard Admin");
            option = lector.nextInt();

            switch (option){
                case 1:
                    dashboard(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while(option > 1);


    }
}
