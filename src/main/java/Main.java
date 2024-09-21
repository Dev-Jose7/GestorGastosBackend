import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner lector = new Scanner(System.in);


    public static void main(String[] args) {
        //Instancias de prueba
        User admin = new User("José", "jfnr398", "1234");
        User user = new User("Fernando" ,"fercho398", "4321");
        admin.getTransactions().createTransaction(1, "Ingreso", 6500, "pagoNomina", user.getCatogories().selectCategory(1));
        admin.getTransactions().createTransaction(1, "Gasto", 1400, "pagoArriendo", user.getCatogories().selectCategory(2));

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
            user.getTransactions().printListIngresos();
            System.out.println("De gastos: ");
            user.getTransactions().printListGastos();
            System.out.println("========OPCIONES=========");
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar gasto");
            System.out.println("3. Modificar transacción");
            System.out.println("4. Eliminar transaccion");
            System.out.println("5. Filtrar transacciones");
            System.out.println("6. Consultar cuenta");
            System.out.println("=========================");
            option = lector.nextInt();

            if(option == 1 || option == 2){
                menuTransaccion(option, user);
            } else if(option == 3){
                updateTransaction(user);
            } else if(option == 4){
                deleteTransaction(user);
            } else if(option == 5){
                filterTransaction(user);
            } else if(option == 6){
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
                user.getTransactions().createTransaction(user.getId(), "Ingreso", value, description, category);
                break;

            case 2:
                user.getTransactions().createTransaction(user.getId(), "Gasto", value, description, category);
                break;

            case 3: //Modificar transacción
                if(register == 1){
                    user.getTransactions().updateTransaction("Ingreso", value, description, category);
                } else if (register == 2){
                    user.getTransactions().updateTransaction("Gasto", value, description, category);
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
            user.getTransactions().printListUser();
            System.out.print("Digite el número de la transacción a modificar: ");
            option = lector.nextInt();

            if(option > user.getTransactions().getListUser().size()){
                System.out.println("Digite un número valido");
            }
        }while (option > user.getTransactions().getListUser().size());

        user.getTransactions().selectTransaction(option);
        menuTransaccion(3, user);
    }

    public static void deleteTransaction(User user){
        int option;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("========ELIMINAR========");
            user.getTransactions().printListUser();
            System.out.print("Digite el número de la transacción a eliminar: ");
            option = lector.nextInt();

            if(option > user.getTransactions().getListUser().size()){
                System.out.println("Digite un número valido");
            }
        }while(option > user.getTransactions().getListUser().size());

        user.getTransactions().selectTransaction(option);
        int confirm;

        do {
            System.out.println("Estas seguro de eliminar esta transacción");
            System.out.println(user.getTransactions().getTargetTransaction());
            System.out.println("1. Si");
            System.out.println("2. No");
            confirm = lector.nextInt();

            switch (confirm) {
                case 1:
                    System.out.println("Transacción eliminada");
                    user.getTransactions().deleteTransaction();
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

    public static void filterTransaction(User user){
        Boolean status = null;
        do{
            System.out.println("======MONEY MANAGER======");
            System.out.println("=========FILTRAR=========");
            System.out.println("Digite el número del tipo de filtro a consultar");
            System.out.println("1. Tipo");
            System.out.println("2. Valor");
            System.out.println("3. Categoria");
            System.out.println("4. Fecha");
            System.out.println("Para iniciar con la consulta digite 0");
            System.out.println("0. Consultar");
            int option = lector.nextInt();

            for (int i = 0; i < user.getTransactions().getDataFilter().length; i++) {
                System.out.println("[" + user.getTransactions().getDataFilter()[i] + "]" );
            }

            if(option != 0){
                status = true;
            }else if (option == 0){
                status = false;
            }

            switch (option){
                case 0:
                    ArrayList<Transaction> filter = user.getTransactions().filter();
                    user.getTransactions().printListFilter(filter);
                    break;

                case 1:
                    typeFilterTransaction(user);
                    break;

                case 2:
                    valueFilterTransaction(user);
                    break;

                case 3:
                    categoryFilterTransaction(user);
                    break;

                case 4:
                    dateFilterTransaction(user);

                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }while(status);
    }

    public static void typeFilterTransaction(User user){
        System.out.println("Digite el número de la opción a seleccionar");
        System.out.println("1. Ingreso");
        System.out.println("2. Gasto");
        int option = lector.nextInt();

        if(option == 1){
            user.getTransactions().getDataFilter()[0] = "Ingreso";
        } else if(option == 2){
            user.getTransactions().getDataFilter()[0] = "Gasto";
        }
    }

    public static void valueFilterTransaction(User user){
        System.out.print("Digite un valor: ");
        int option = lector.nextInt();

        user.getTransactions().getDataFilter()[1] = option + "";
    }

    public static void categoryFilterTransaction(User user){
        int option;
        String category = "";
        do{
            System.out.println("Digite el número de la opción a seleccionar");
            user.getCatogories().printCategoriesByUser();
            option = lector.nextInt();
            if(option > 0 && option <= user.getCatogories().categoriesByUserSize()){
                category = user.getCatogories().selectCategory(option);
            }else if(option <= 0 && option > user.getCatogories().categoriesByUserSize()){
                System.out.println("Digite un número entre 1 y " + user.getCatogories().categoriesByUserSize());
            }
        }while(option > user.getCatogories().categoriesByUserSize());

        user.getTransactions().getDataFilter()[2] = category;
    }

    private static void dateFilterTransaction(User user) {
        System.out.print("Digite la fecha a consultar de la siguiente forma: " + LocalDate.now());
        String option = lector.next();

        user.getTransactions().getDataFilter()[3] = option;
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