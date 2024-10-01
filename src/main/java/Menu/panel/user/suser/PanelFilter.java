package Menu.panel.user.suser;

import Classes.Main;
import Classes.account.User;

public class PanelFilter {
    public static void filterUser(User user){
        String option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=========FILTRAR=========");
            user.getUserFilter().emptyDatafilter();
            for (int i = 0; i < user.getUserFilter().getDataFilter().length; i++) {
                System.out.print("[" + user.getUserFilter().getDataFilter()[i] + "]  " );
            }
            System.out.println("");
            System.out.println("Digite el número del tipo de filtro a consultar");
            System.out.println("Para iniciar con la consulta digite 0");
            System.out.println("Para limpiar los filtros digite -");
            System.out.println("1. ID");
            System.out.println("2. Nombre");
            System.out.println("3. Correo electronico");
            System.out.println("============");
            System.out.println("0. Consultar");
            option = Main.lector.next();

            switch (option){
                case "-":
                    user.getUserFilter().clearDataFilter();
                    break;

                case "0":
                    FilterUser.resultFilterUser(user);
                    break;

                case "1":
                    idFilterUser(user);
                    break;

                case "2":
                    nameFilterUser(user);
                    break;

                case "3":
                    emailFilterUser(user);
                    break;

                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }while(!option.equals("0"));
    }

    private static void idFilterUser(User user) {
        System.out.println("Digite el id a consultar");
        String option = Main.lector.next();

        user.getUserFilter().getDataFilter()[0] = option;
    }

    private static void nameFilterUser(User user) {
        System.out.println("Digite el nombre a consultar");
        String option = Main.lector.next();

        user.getUserFilter().getDataFilter()[1] = option;
    }

    private static void emailFilterUser(User user) {
        System.out.println("Digite el correo electronico a consultar");
        String option = Main.lector.next();

        user.getUserFilter().getDataFilter()[2] = option;
    }

}
