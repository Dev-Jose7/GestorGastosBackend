package Menu;

import Classes.User;
import Classes.Main;

public class Categories {
    public static void categoriesUser(User user){
        int option;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====LISTA CATEGORIAS====");
            user.getCatogories().printCategoriesByUser();
            System.out.println("");
            System.out.println("Digite el número de la opción a eligir");
            System.out.println("1. Crear categoria");
            System.out.println("2. Eliminar categoria");
            System.out.println("3. Consultar cuenta");
            option = Main.lector.nextInt();

            switch (option){
                case 1:
                    addCategory(user);
                    break;

                case 2:
                    deleteCategory(user);
                    break;

                case 3:
                    Dashboard.selectUser(user);
                    break;
            }
        }while(option > 3);
    }

    public static void addCategory(User user) {
        boolean status;
        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====AÑADIR CATEGORIA====");
            System.out.print("Digite un nombre a la categoria: ");
            String categoryName = Main.lector.next();

            status = user.getCatogories().validateCategory(categoryName);

            if(status){
                System.out.println("Esta categoria ya existe en la lista, por favor cree una nueva");
            }else if(status == false){
                user.getCatogories().addCategories(categoryName);
                System.out.println("Categoria creada con exito");
                categoriesUser(user);
            }
        }while(status);

        Dashboard.menu(user);
    }

    public static void deleteCategory(User user){
        int confirm;

        do{
            System.out.println("");
            System.out.println("======MONEY MANAGER======");
            System.out.println("=====ELIMINAR CATEGORIA====");
            user.getCatogories().printCategoriesByUser();
            System.out.println("Digite el número de la categoria a eliminar");
            int option = Main.lector.nextInt();

            System.out.println("¿Esta seguro de eliminar esta categoria?: " + user.getCatogories().selectCategory(option));
            System.out.println("1. Si");
            System.out.println("2. No");
            confirm = Main.lector.nextInt();

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

        Dashboard.menu(user);
    }
}
