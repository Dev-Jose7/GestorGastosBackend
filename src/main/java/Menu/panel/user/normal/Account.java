package Menu.panel.user.normal;

import Classes.Main;
import Classes.account.User;
import Menu.panel.Dashboard;
import Menu.panel.user.suser.Admin;

import java.util.ArrayList;

public class Account {
    public static void selectUser(User user, ArrayList<User> databaseUsers){
        int option;
        printDatabase(databaseUsers);

        do{
            System.out.print("Digite el número del usuario a seleccionar: ");
            option = Main.lector.nextInt();
        }while(option < 1 || option > databaseUsers.size());

        user.getUserManager().selectTransaction(option, databaseUsers);
    }

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
        }while (option < 1 || option > 4);

        if(Admin.statusUpdateAdmin){
            Admin.dashboard(Admin.suser);
            Admin.statusUpdateAdmin = false;
        }else {
            Dashboard.menu(user);
        }
    }

    public static void deleteUser(User user){
        int confirm;

        do {
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====ELIMINAR USUARIO====");
            System.out.println("Estas seguro de eliminar esta transacción");
            System.out.println(user.getUserManager().getTargetUser());
            System.out.println("1. Si");
            System.out.println("2. No");
            confirm = Main.lector.nextInt();

            switch (confirm) {
                case 1:
                    System.out.println("Usuario eliminado");
                    user.getUserManager().deleteUser();
                    break;

                case 2:
                    Admin.dashboard(user);
                    break;

                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }while (confirm < 1 || confirm > 2);

        Admin.dashboard(user);
    }

    public static void printDatabase(ArrayList<User> databaseUser){
        System.out.println("");
        for (int i = 0; i < databaseUser.size(); i++) {
            System.out.println(i+1 + ". " + databaseUser.get(i));
        }
        System.out.println("");
    }
}