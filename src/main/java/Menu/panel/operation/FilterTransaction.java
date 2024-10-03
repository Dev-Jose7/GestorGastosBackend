package Menu.panel.operation;

import Classes.account.User;
import Classes.Main;
import Classes.operation.Transaction;
import Menu.panel.Dashboard;
import Menu.panel.user.suser.Admin;

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
        int option;

        do {
            System.out.println("Digite el número de la opción a utilizar");
            System.out.println("1. Modificar transacción");
            System.out.println("2. Eliminar transacción");
            System.out.println("3. Dashboard");
            option = Main.lector.nextInt();

            switch(option){
                case 1:
                    updateTransactionFilter(user, user.getTransactions().getListFilter());
                    break;

                case 2:
                    deleteTransactionFilter(user, user.getTransactions().getListFilter());
                    break;

                case 3:
                    if(Admin.statusUpdateAdmin){
                        Admin.dashboard(Admin.suser);
                    }else {
                        Dashboard.menu(user);
                    }
                    break;

                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }while(option < 0 || option > 3);

    }

    public static void updateTransactionFilter(User user, ArrayList<Transaction> databaseFilter){
        Admin.suser = user;
        Admin.statusUpdateAdmin = true;
        PanelTransaction.selectTransaction(user, databaseFilter);
        PanelTransaction.updateTransaction(user);
    }

    public static void deleteTransactionFilter(User user, ArrayList<Transaction> databaseFilter){
        Admin.suser = user;
        Admin.statusUpdateAdmin = true;
        PanelTransaction.selectTransaction(user, databaseFilter);
        PanelTransaction.deleteTransaction(user);
    }
}
