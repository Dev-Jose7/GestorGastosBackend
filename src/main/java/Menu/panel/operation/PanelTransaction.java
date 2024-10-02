package Menu.panel.operation;

import Classes.account.User;
import Classes.Main;
import Classes.operation.Transaction;
import Menu.panel.Dashboard;

import java.util.ArrayList;

public class PanelTransaction {
    public static void selectTransaction(User user, ArrayList<Transaction> databaseTransactions){
        int option;
        user.getTransactionManager().printDatabase(databaseTransactions);

        do{
            System.out.println("Para cancelar digite cero (0)");
            System.out.print("Digite el número de la transacción a seleccionar: ");
            option = Main.lector.nextInt();
            if(option == 0){
                Dashboard.menu(user);
            }
        }while(option < 0 || option > databaseTransactions.size());

        user.getTransactionManager().selectTransaction(option, databaseTransactions);
    }

    public static void updateTransaction(User user){
        System.out.println("");
        System.out.println("Transacción a modificar: ");
        System.out.println(user.getTransactionManager().getTargetTransaction());
        Dashboard.statusUpdate = true;
        Dashboard.menuTransaction(user);
    }

    public static void deleteTransaction(User user){
        int confirm;

        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========ELIMINAR========");
            System.out.println("Estas seguro de eliminar esta transacción");
            System.out.println(user.getTransactionManager().getTargetTransaction());
            System.out.println("1. Si");
            System.out.println("2. No");
            confirm = Main.lector.nextInt();

            switch (confirm) {
                case 1:
                    System.out.println("Transacción eliminada");
                    user.getTransactionManager().deleteTransaction();
                    Dashboard.menu(user);
                    break;

                case 2:
                    Dashboard.menu(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (confirm < 1 || confirm > 2);
    }
}
