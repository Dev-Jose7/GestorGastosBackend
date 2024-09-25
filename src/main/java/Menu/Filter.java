package Menu;

import Classes.User;
import Classes.Main;

import java.sql.Array;
import java.sql.SQLOutput;

public class Filter {
    public static void filterTransaction(User user){
        String option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=========FILTRAR=========");
            user.getTransactions().emptyDatafilter();
            for (int i = 0; i < user.getTransactions().getDataFilter().length; i++) {
                System.out.print("[" + user.getTransactions().getDataFilter()[i] + "]  " );
            }
            System.out.println("");
            System.out.println("Digite el número del tipo de filtro a consultar");
            System.out.println("Para iniciar con la consulta digite 0");
            System.out.println("Para limpiar los filtros digite -");
            System.out.println("1. Tipo");
            System.out.println("2. Valor");
            System.out.println("3. Categoria");
            System.out.println("4. Fecha");
            System.out.println("============");
            System.out.println("0. Consultar");
            option = Main.lector.next();

            switch (option){
                case "-":
                    user.getTransactions().clearDataFilter();
                    break;

                case "0":
                    FilterTransaction.resultFilterTransaction(user);
                    break;

                case "1":
                    typeFilterTransaction(user);
                    break;

                case "2":
                    valueFilterTransaction(user);
                    break;

                case "3":
                    categoryFilterTransaction(user);
                    break;

                case "4":
                    dateFilterTransaction(user);

                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }while(!option.equals("0"));
    }

    public static void typeFilterTransaction(User user){
        System.out.println("Digite el número de la opción a seleccionar");
        System.out.println("1. Ingreso");
        System.out.println("2. Gasto");
        int option = Main.lector.nextInt();

        if(option == 1){
            user.getTransactions().getDataFilter()[0] = "Ingreso";
        } else if(option == 2){
            user.getTransactions().getDataFilter()[0] = "Gasto";
        }
    }

    public static void valueFilterTransaction(User user){
        System.out.print("Digite un valor: ");
        int option = Main.lector.nextInt();

        user.getTransactions().getDataFilter()[1] = option + "";
    }

    public static void categoryFilterTransaction(User user){
        int option;
        String category = "";
        do{
            System.out.println("Digite el número de la opción a seleccionar");
            user.getCatogories().printCategoriesByUser();
            option = Main.lector.nextInt();
            if(option > 0 && option <= user.getCatogories().categoriesByUserSize()){
                category = user.getCatogories().selectCategory(option);
            }else if(option <= 0 && option > user.getCatogories().categoriesByUserSize()){
                System.out.println("Digite un número entre 1 y " + user.getCatogories().categoriesByUserSize());
            }
        }while(option > user.getCatogories().categoriesByUserSize());

        user.getTransactions().getDataFilter()[2] = category;
    }

    private static void dateFilterTransaction(User user) {
        System.out.println("Digite la fecha a consultar de la siguiente forma: 'dd-mm-yyyy'");
        String option = Main.lector.next();

        user.getTransactions().getDataFilter()[3] = option;
    }
}