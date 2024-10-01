package Menu.panel.user.suser;

import Classes.Main;
import Classes.account.User;
import Menu.panel.Dashboard;
import Menu.panel.user.normal.Account;

public class FilterUser {
    public static void resultFilterUser(User user){
        user.getUserFilter().filter();
        System.out.println("");
        System.out.println("RESULTADO DE LA BUSQUEDA");
        System.out.println("=======================================================================================================");
        user.getUserFilter().printListFilter();
        System.out.println("=======================================================================================================");
        System.out.println("");
        System.out.println("Digite el número de la opción a utilizar");

        System.out.println("1. Ingresar al usuario");
        System.out.println("2. Modificar usuario");
        System.out.println("3. Eliminar usuario");
        System.out.println("4. Dashboard");
        int option = Main.lector.nextInt();

        switch(option){
            case 1:
                selectUserFilter(user);
                break;

            case 2:
                updateUserFilter(user);
                break;

            case 3:
                deleteUserFilter(user);
                break;

            case 4:
                Dashboard.menu(user);
                break;

            default:
                System.out.println("Opción invalida");
                break;
        }
    }

    public static void selectUserFilter(User user){
        Account.selectUser(user, user.getListFilter());
        Dashboard.menu(user.getUserManager().getTargetUser());
    }

    public static void updateUserFilter(User user){
        Admin.updateUserData(user, user.getListFilter());
    }

    public static void deleteUserFilter(User user){
        Admin.deleteUserData(user, user.getListFilter());
    }
}
