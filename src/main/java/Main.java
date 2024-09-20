import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner lector = new Scanner(System.in);


    public static void main(String[] args) {
        //Instancias de prueba
        User admin = new User("José", "jfnr398", "1234");
        User user = new User("Fernando" ,"fercho398", "4321");
        Transaction transaction = new Ingreso(1, 6500, "pagoNomina", "Salario");
        Transaction transaction1 = new Gasto(1, 1400, "pagoArriendo", "Arriendo");
        menu();
    }

    public static void menu() {
        int option;
        do {
            System.out.println("======MONEY MANAGER======");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrate");
            System.out.println("3. Salir");
            option = lector.nextInt();

            switch (option) {
                case 1:
                    menuSesion();
                    break;

                case 2:
                    menuRegistro();
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Digite una opcion correcta");
                    break;
            }
        } while (option > 3);
    }

    public static void menuSesion(){
        System.out.println("======MONEY MANAGER======");
        System.out.println("======INICIAR SESION=====");
        System.out.print("Digite su correo: ");
        String email = lector.next();
        System.out.print("Digite su contraseña: ");
        String password = lector.next();

        Boolean status = User.validateUser(email, password);

        if(status == false){
            System.out.println("Correo o contraseña incorrectos");
            menu();
        }
    }

    public static void menuRegistro(){
        System.out.println("======MONEY MANAGER======");
        System.out.println("=========REGISTRO========");
        System.out.print("Digite su nombre: ");
        String name = lector.next();
        System.out.print("Digite su correo: ");
        String email = lector.next();
        System.out.print("Digite su contraseña: ");
        String password = lector.next();

        User user = new User(name, email, password);
        System.out.println("Usuario creado");
        menu();
    }

    public static void dashboard(User user) {
        int option;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("========DASHBOARD========");
            System.out.println("=========BALANCE=========");
            System.out.println("TOTAL: " + user.sumaTotal());
            System.out.println("En ingresos: " + user.sumaIngresos());
            System.out.println("En gastos: " + user.sumaGastos());
            System.out.println("======TRANSACCIONES======");
            System.out.println("De ingresos: ");
            user.printIngreso();
            System.out.println("De gastos: ");
            user.printGasto();
            System.out.println("========OPCIONES=========");
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar gasto");
            System.out.println("3. Modificar transacción");
            System.out.println("4. Eliminar transaccion");
            System.out.println("5. Consultar cuenta");
            System.out.println("=========================");
            option = lector.nextInt();

            if(option == 1 || option == 2){
                menuTransaccion(option, user);
            } else if(option == 3){
                updateTransaction(user);
            } else if(option == 4){
                user.getTransactionOnList();
                deleteTransaction(user);
            } else if(option == 5){
                selectUser(user);
            }
        }while(option > 3);
    }

    private static void menuTransaccion(int type, User user){
        int option = 0;
        int register = 0;
        System.out.println("======MONEY MANAGER======");
        System.out.println("=====REG TRANSACCION=====");

        if(type == 3){
            System.out.println(user.selectTransaction);
            System.out.println("Digite el tipo de transacción: ");
            System.out.println("1. Ingreso");
            System.out.println("2. Gasto");
            register = lector.nextInt();
        }
        System.out.print("Digite un valor: ");
        int value = lector.nextInt();
        System.out.print("Digite una descripción: ");
        String description = lector.next();
        lector.nextLine();

        String category = "";

        do{
            System.out.println("Escoja una categoria: ");
            user.getCatogories().printCategoriesByUser();
            option = lector.nextInt();
            if(option > 0 && option <= user.getCatogories().categoriesByUserSize()){
                category = user.getCatogories().selectCategory(option);
            }else if(option <= 0 && option > user.getCatogories().categoriesByUserSize()){
                System.out.println("Digite un número entre 1 y " + user.getCatogories().categoriesByUserSize());
            }
        }while(option > user.getCatogories().categoriesByUserSize());

        switch (type){
            case 1:
                user.createTransaction("Ingreso", value, description, category);
                break;

            case 2:
                user.createTransaction("Gasto", value, description, category);
                break;

            case 3: //Modificar transacción
                if(register == 1){
                    user.updateTransaction("Ingreso", value, description, category);
                } else if (register == 2){
                    user.updateTransaction("Gasto", value, description, category);
                }
                break;
        }

        dashboard(user);
    }

    public static void updateTransaction(User user){
        int option;
        do {
            System.out.println("======MONEY MANAGER======");
            System.out.println("========MODIFICAR========");
            user.printTransactionOnList();
            System.out.print("Digite el número de la transacción a modificar: ");
            option = lector.nextInt();

            if(option > user.getTransactionOnList().size()){
                System.out.println("Digite un número valido");
            }
        }while (option > user.getTransactionOnList().size());

        user.takeTransaction(option);
        menuTransaccion(3, user);
    }

    public static void deleteTransaction(User user){
        int option;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("========ELIMINAR========");
            user.printTransactionOnList();
            System.out.print("Digite el número de la transacción a eliminar: ");
            option = lector.nextInt();

            if(option > user.getTransactionOnList().size()){
                System.out.println("Digite un número valido");
            }
        }while(option > user.getTransactionOnList().size());

        user.takeTransaction(option);
        int confirm;

        do {
            System.out.println("Estas seguro de eliminar esta transacción");
            System.out.println(user.selectTransaction);
            System.out.println("1. Si");
            System.out.println("2. No");
            confirm = lector.nextInt();

            switch (confirm) {
                case 1:
                    System.out.println("Transacción eliminada");
                    user.deleteTransaction();
                    dashboard(user);
                    break;

                case 2:
                    dashboard(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (confirm > 2);
    }

    public static void selectUser(User user){
        int option;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("======CUENTA USUARIO=====");
            System.out.println("Nombre: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Ingresos registrados: " + user.getTotalIngreso());
            System.out.println("Gastos registrados: " + user.getTotalGasto());
            System.out.println("1. Dashboard");
            System.out.println("2. Modificar datos");
            System.out.println("3. Lista de categorias");
            System.out.println("4. Cerrar sesión");
            System.out.println("=========================");
            option = lector.nextInt();

            switch (option){
                case 1:
                    dashboard(user);
                    break;

                case 2:
                    updateUser(user);
                    break;

                case 3:
                    categoriesUser(user);
                    break;

                case 4:
                    user.logout();
                    break;

                default:
                    System.out.println("Digite una opcion correcta");
                    break;
            }
        }while(option > 3);
    }

    public static void updateUser(User user){
        int option;
        do {
            System.out.println("======MONEY MANAGER======");
            System.out.println("====MODIFICAR USUARIO====");
            System.out.println("1. Cambiar nombre: " + user.getName());
            System.out.println("2. Cambiar email: " + user.getEmail());
            System.out.println("3. Cambiar contraseña: " + user.getPassword());
            System.out.println("4. Consultar cuenta");
            option = lector.nextInt();

            switch (option){
                case 1:
                    System.out.print("Digite un nuevo nombre: ");
                    String name = lector.next();

                    System.out.println("Su nombre ha sido cambiado");
                    user.setName(name);
                    break;

                case 2:
                    System.out.print("Digite un nuevo email: ");
                    String email = lector.next();

                    System.out.println("Su correo ha sido cambiado");
                    user.setEmail(email);
                    break;

                case 3:
                    String password;
                    String passwordConfirm;
                    do{
                        System.out.print("Digite una nueva contraseña: ");
                        password = lector.next();
                        System.out.print("Confirme su nueva contraseña: ");
                        passwordConfirm = lector.next();

                        if(password.equals(passwordConfirm)){
                            System.out.println("Su contraseña ha sido cambiada");
                            user.setPassword(password);
                            dashboard(user);
                        }else {
                            System.out.println("Las contraseñas no coinciden");
                        }
                    }while (!password.equals(passwordConfirm));
                    break;

                case 4:
                    selectUser(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (option > 4);
    }

    public static void categoriesUser(User user){
        int option;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====LISTA CATEGORIAS====");
            user.getCatogories().printCategoriesByUser();
            System.out.println("");
            System.out.println("Digite el número de la opción a eligir");
            System.out.println("1. Crear categoria");
            System.out.println("2. Eliminar categoria");
            System.out.println("3. Consultar cuenta");
            option = lector.nextInt();

            switch (option){
                case 1:
                    addCategory(user);
                    break;

                case 2:
                    deleteCategory(user);
                    break;

                case 3:
                    selectUser(user);
                    break;
            }
        }while(option > 3);
    }

    public static void addCategory(User user) {
        Boolean status;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====AÑADIR CATEGORIA====");
            System.out.println("Digite un nombre a la categoria");
            String categoryName = lector.next();

            status = user.getCatogories().validateCategory(categoryName);

            if(status){
                System.out.println("Esta categoria ya existe en la lista, por favor cree una nueva");
            }else if(status == false){
                user.getCatogories().addCategories(categoryName);
                System.out.println("Categoria creada con exito");
                categoriesUser(user);
            }
        }while(status);
    }

    public static void deleteCategory(User user){
        int confirm;

        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====ELIMINAR CATEGORIA====");
            user.getCatogories().printCategoriesByUser();
            System.out.println("Digite el número de la categoria a eliminar");
            int option = lector.nextInt();

            System.out.println("¿Esta seguro de eliminar esta categoria?: " + user.getCatogories().selectCategory(option));
            System.out.println("1. Si");
            System.out.println("2. No");
            confirm = lector.nextInt();

            switch (confirm){
                case 1:
                    if(option > 0 && option <= user.getCatogories().categoriesByUserSize()){
                        user.getCatogories().deleteCategories(option);
                        System.out.println("Categoria eliminada");
                    } else if(option <= 0 && option > user.getCatogories().categoriesByUserSize()){
                        System.out.println("Digite un número valido entre 1 y " + user.getCatogories().categoriesByUserSize());
                    }
                    break;

                case 2:
                    categoriesUser(user);
                    break;
            }
        }while(confirm > 2);
    }
}