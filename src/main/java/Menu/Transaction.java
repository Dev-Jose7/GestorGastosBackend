package Menu;

import Classes.User;
import Classes.Main;

public class Transaction {
    public static void updateTransaction(User user){
        int option;
        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========MODIFICAR========");
            user.getTransactions().printListUser();
            System.out.print("Digite el número de la transacción a modificar: ");
            option = Main.lector.nextInt();
            if(option > user.getTransactions().getListUser().size()){
                System.out.println("Digite un número valido");
            }
        }while (option > user.getTransactions().getListUser().size());

        user.getTransactions().selectTransaction("allList", option);
        Dashboard.statusUpdate = true;
        Dashboard.menuTransaction(user);
    }

    public static void deleteTransaction(User user){
        int option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========ELIMINAR========");
            user.getTransactions().printListUser();
            System.out.print("Digite el número de la transacción a eliminar: ");
            option = Main.lector.nextInt();

            if(option > user.getTransactions().getListUser().size()){
                System.out.println("Digite un número valido");
            }
        }while(option > user.getTransactions().getListUser().size());

        user.getTransactions().selectTransaction("allList", option);
        int confirm;

        do {
            System.out.println("Estas seguro de eliminar esta transacción");
            System.out.println(user.getTransactions().getTargetTransaction());
            System.out.println("1. Si");
            System.out.println("2. No");
            confirm = Main.lector.nextInt();

            switch (confirm) {
                case 1:
                    System.out.println("Transacción eliminada");
                    user.getTransactions().deleteTransaction();
                    Dashboard.menu(user);
                    break;

                case 2:
                    Dashboard.menu(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (confirm > 2);
    }
}
