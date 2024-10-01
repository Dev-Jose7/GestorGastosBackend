package Menu.panel.operation;

import Classes.account.User;
import Classes.Main;
import Classes.operation.Transaction;
import Menu.panel.Dashboard;

import java.util.ArrayList;

public class FilterTransaction {
    public static void resultFilterTransaction(User user, ArrayList<Transaction> databaseTransactions){
        user.getTransactionFilter().filter(databaseTransactions);
        System.out.println("");
        System.out.println("RESULTADO DE LA BUSQUEDA");
        System.out.println("=======================================================================================================");
        user.getTransactionFilter().printListFilter();
        System.out.println("=======================================================================================================");
        System.out.println("");
        System.out.println("Digite el número de la opción a utilizar");
        System.out.println("1. Modificar transacción");
        System.out.println("2. Eliminar transacción");
        System.out.println("3. Dashboard");
        int option = Main.lector.nextInt();

        switch(option){
            case 1:
                updateTransactionFilter(user, user.getTransactions().getListFilter());
                break;

            case 2:
                deleteTransactionFilter(user, user.getTransactions().getListFilter());
                break;

            case 3:
                Dashboard.menu(user);
                break;

            default:
                System.out.println("Opción invalida");
                break;
        }
    }

    public static void updateTransactionFilter(User user, ArrayList<Transaction> databaseFilter){
        PanelTransaction.updateTransaction(user, databaseFilter);
    }

    public static void deleteTransactionFilter(User user, ArrayList<Transaction> databaseFilter){
        PanelTransaction.deleteTransaction(user, databaseFilter);
    }
}
