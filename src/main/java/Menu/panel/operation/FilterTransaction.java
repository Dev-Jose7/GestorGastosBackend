package Menu.panel.operation;

import Classes.account.User;
import Classes.Main;
import Menu.panel.Dashboard;

public class FilterTransaction {
    public static void resultFilterTransaction(User user){
        user.getTransactionFilter().filter();
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
                updateTransactionFilter(user);
                break;

            case 2:
                deleteTransactionFilter(user);
                break;

            case 3:
                Dashboard.menu(user);
                break;

            default:
                System.out.println("Opción invalida");
                break;
        }
    }

    public static void updateTransactionFilter(User user){
        int option;
        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========MODIFICAR========");
            user.getTransactionFilter().printListFilter();
            System.out.print("Digite el número de la transacción a modificar: ");
            option = Main.lector.nextInt();
            if(option > user.getTransactions().getListFilter().size()){
                System.out.println("Digite un número valido");
            }
        }while (option < 1 || option > user.getTransactions().getListFilter().size());

        user.getTransactionManager().selectTransaction("filterList", option);
        Dashboard.statusUpdate = true;
        Dashboard.menuTransaction(user);
    }

    public static void deleteTransactionFilter(User user){
        int option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========ELIMINAR========");
            user.getTransactionFilter().printListFilter();
            System.out.print("Digite el número de la transacción a eliminar: ");
            option = Main.lector.nextInt();

            if(option > user.getTransactions().getListFilter().size()){
                System.out.println("Digite un número valido");
            }
        }while(option < 1 || option > user.getTransactions().getListFilter().size());

        user.getTransactionManager().selectTransaction("filterList", option);
        int confirm;

        do {
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
