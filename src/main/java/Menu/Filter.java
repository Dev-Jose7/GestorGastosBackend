package Menu;

import Classes.User;
import Classes.Main;

public class Filter {
    public static void filterTransaction(User user){
        String option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=========FILTRAR=========");
            user.getTransactionManager().getTransactionFilter().emptyDatafilter();
            for (int i = 0; i < user.getTransactionManager().getTransactionFilter().getDataFilter().length; i++) {
                System.out.print("[" + user.getTransactionManager().getTransactionFilter().getDataFilter()[i] + "]  " );
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
                    user.getTransactionManager().getTransactionFilter().clearDataFilter();
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
            user.getTransactionManager().getTransactionFilter().getDataFilter()[0] = "Ingreso";
        } else if(option == 2){
            user.getTransactionManager().getTransactionFilter().getDataFilter()[0] = "Gasto";
        }
    }

    public static void valueFilterTransaction(User user) {
        String minor;
        String major;
        boolean status = false;
        System.out.print("Digite un valor para establecer un inicio al rango: ");
        minor = Main.lector.next();
        user.getTransactionManager().getTransactionFilter().getDataFilter()[1] = minor;

        do{
            System.out.println("Digite un valor mayor a " + user.getTransactionManager().getTransactionFilter().getDataFilter()[1] + " para establecer un limite al rango");
            System.out.println("Si no desea establecer un rango digite 0");
            major = Main.lector.next();

            user.getTransactionManager().getTransactionFilter().getDataFilter()[2] = major;
            if(Integer.parseInt(major) < Integer.parseInt(minor)){
                status = true;
            }else {
                status = false;
            }

            if(major.equals("0")){
                status = false;
            }
        }while(status);
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

        user.getTransactionManager().getTransactionFilter().getDataFilter()[3] = category;
    }

    private static void dateFilterTransaction(User user) {
        System.out.println("Digite la fecha a consultar de la siguiente forma: 'dd-mm-yyyy'");
        String option = Main.lector.next();

        user.getTransactionManager().getTransactionFilter().getDataFilter()[4] = option;
    }
}