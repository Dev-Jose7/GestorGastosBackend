package Menu;

import Classes.Main;
import Classes.User;

public class Account {
    public static void updateUser(User user){
        int option;
        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("====MODIFICAR USUARIO====");
            System.out.println("1. Cambiar nombre: " + user.getName());
            System.out.println("2. Cambiar email: " + user.getEmail());
            System.out.println("3. Cambiar contraseña: " + user.getPassword());
            System.out.println("4. Consultar cuenta");
            option = Main.lector.nextInt();

            switch (option){
                case 1:
                    System.out.print("Digite un nuevo nombre: ");
                    String name = Main.lector.next();

                    System.out.println("Su nombre ha sido cambiado");
                    user.setName(name);
                    break;

                case 2:
                    System.out.print("Digite un nuevo email: ");
                    String email = Main.lector.next();

                    System.out.println("Su correo ha sido cambiado");
                    user.setEmail(email);
                    break;

                case 3:
                    String password;
                    String passwordConfirm;
                    do{
                        System.out.print("Digite una nueva contraseña: ");
                        password = Main.lector.next();
                        System.out.print("Confirme su nueva contraseña: ");
                        passwordConfirm = Main.lector.next();

                        if(password.equals(passwordConfirm)){
                            System.out.println("Su contraseña ha sido cambiada");
                            user.setPassword(password);
                            Dashboard.menu(user);
                        }else {
                            System.out.println("Las contraseñas no coinciden");
                        }
                    }while (!password.equals(passwordConfirm));
                    break;

                case 4:
                    Dashboard.selectUser(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (option > 4);

        Dashboard.menu(user);
    }
}