package Menu;

import Classes.Category;
import Classes.Main;
import Classes.User;
import Menu.Categories;

import java.sql.SQLOutput;

public class Dashboard {
    public static boolean statusUpdate;
    public static void menu(User user) {
        int option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========DASHBOARD========");
            System.out.println("=========BALANCE=========");
            System.out.println("TOTAL: " + user.sumaTotal());
            System.out.println("En ingresos: " + user.sumaIngresos());
            System.out.println("En gastos: " + user.sumaGastos());
            System.out.println("======TRANSACCIONES======");
            System.out.println("De ingresos: ");
            user.getTransactions().printListIngresos();
            System.out.println("");
            System.out.println("De gastos: ");
            user.getTransactions().printListGastos();
            System.out.println("========OPCIONES=========");
            System.out.println("1. Registrar transacción");
            System.out.println("2. Gestionar transacciones");
            System.out.println("3. Consultar cuenta");
            System.out.println("=========================");
            option = Main.lector.nextInt();

            if(option == 1){
                menuTransaction(user);
            }else if(option == 2) {
                manageTransaction(user);
            }else if(option == 3){
                selectUser(user);
            }
        }while(option > 3);
    }

    public static void menuTransaction(User user){
        int option = 0;
        int type= 0;
        System.out.println("");
        System.out.println("======MONEY MANAGER======");
        System.out.println("=====REG TRANSACCION=====");

        do {
            System.out.println("Digite el tipo de transacción: ");
            System.out.println("1. Ingreso");
            System.out.println("2. Gasto");
            type = Main.lector.nextInt();
        }while(type > 2);

        System.out.print("Digite un valor: ");
        int value = Main.lector.nextInt();
        System.out.print("Digite una descripción: ");
        String description = Main.lector.next();
        Main.lector.nextLine();

        String category = "";

        do{
            System.out.println("Escoja una categoria: ");
            user.getCatogories().printCategoriesByUser();
            option = Main.lector.nextInt();
            if(option > 0 && option <= user.getCatogories().categoriesByUserSize()){
                category = user.getCatogories().selectCategory(option);
            }else if(option <= 0 && option > user.getCatogories().categoriesByUserSize()){
                System.out.println("Digite un número entre 1 y " + user.getCatogories().categoriesByUserSize());
            }
        }while(option > user.getCatogories().categoriesByUserSize());

        if(statusUpdate == false){
            switch (type){
                case 1:
                    user.getTransactions().createTransaction(user.getId(), "Ingreso", value, description, category);
                    break;
                case 2:
                    user.getTransactions().createTransaction(user.getId(), "Gasto", value, description, category);
                    break;
            }
        }else if(statusUpdate == true){
            if(type == 1){
                user.getTransactions().updateTransaction("Ingreso", value, description, category);
            } else if (type == 2){
                user.getTransactions().updateTransaction("Gasto", value, description, category);
            }
            statusUpdate = false;
        }

        menu(user);
    }

    public static void manageTransaction(User user){
        int option;
        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("========GESTIONAR========");

            System.out.println("Elija una opción: ");
            System.out.println("1. Modificar");
            System.out.println("2. Eliminar");
            System.out.println("3. Filtrar");
            option = Main.lector.nextInt();

            switch (option){
                case 1:
                    Transaction.updateTransaction(user);
                    break;

                case 2:
                    Transaction.deleteTransaction(user);
                    break;

                case 3:
                    Filter.filterTransaction(user);
                    break;

                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }while (option > 2);
    }

    public static void selectUser(User user){
        int option;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("======CUENTA USUARIO=====");
            System.out.println("Nombre: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Ingresos registrados: " + user.getTransactions().getTotalIngreso());
            System.out.println("Gastos registrados: " + user.getTransactions().getTotalGasto());
            System.out.println("1. Dashboard");
            System.out.println("2. Modificar datos");
            System.out.println("3. Lista de categorias");
            System.out.println("4. Cerrar sesión");
            System.out.println("=========================");
            option = Main.lector.nextInt();

            switch (option){
                case 1:
                    menu(user);
                    break;

                case 2:
                    Account.updateUser(user);
                    break;

                case 3:
                    Categories.categoriesUser(user);
                    break;

                case 4:
                    user.logout(user);
                    break;

                default:
                    System.out.println("Digite una opcion correcta");
                    break;
            }
        }while(option > 3);
    }
}
