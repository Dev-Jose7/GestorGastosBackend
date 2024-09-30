package Menu.main;

import Classes.Main;
import Classes.account.User;

public class Index {
    public static void main() {
        int option;
        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrate");
            System.out.println("3. Salir");
            option = Main.lector.nextInt();

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
        } while (option < 1 || option > 3);
    }

    public static void menuSesion(){
        System.out.println("");
        System.out.println("======MONEY MANAGER======");
        System.out.println("======INICIAR SESION=====");
        System.out.print("Digite su correo: ");
        String email = Main.lector.next();
        System.out.print("Digite su contraseña: ");
        String password = Main.lector.next();

        boolean status = User.validateUser(email, password);

        if(status == false){
            System.out.println("Correo o contraseña incorrectos");
            main();
        }
    }

    public static void menuRegistro(){
        System.out.println("");
        System.out.println("======MONEY MANAGER======");
        System.out.println("=========REGISTRO========");
        System.out.print("Digite su nombre: ");
        String name = Main.lector.next();
        System.out.print("Digite su correo: ");
        String email = Main.lector.next();
        System.out.print("Digite su contraseña: ");
        String password = Main.lector.next();

        User user = new User(name, email, password);
        System.out.println("Usuario creado");
        main();
    }
}
