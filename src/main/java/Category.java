import java.util.ArrayList;

public class Category {
    private static ArrayList<String> categoriesMain= new ArrayList<>();
    private ArrayList<String> categoriesUser = new ArrayList<>();

    public Category(){
        defaultCategories();
        categoriesByUser();
    }

    public void defaultCategories(){
        categoriesMain.add("Salario");
        categoriesMain.add("Arriendo");
        categoriesMain.add("Ingreso");
        categoriesMain.add("Servicios");
        categoriesMain.add("Transporte");
        categoriesMain.add("Alimentación");
        categoriesMain.add("Entretenimiento");
        categoriesMain.add("Compras");
        categoriesMain.add("Varios");
    }

    public void categoriesByUser(){
        for (int i = 0; i < categoriesMain.size(); i++) {
            this.categoriesUser.add(categoriesMain.get(i));
        }
    }

    public void printCategoriesByUser(){
        for (int i = 0; i < this.categoriesUser.size(); i++) {
            System.out.println(i+1 + "." + " " + this.categoriesUser.get(i));
        }
    }

    public int categoriesByUserSize(){
        return this.categoriesUser.size();
    }

    public String selectCategory(int option) {
        return this.categoriesUser.get(option - 1);
    }

    public Boolean validateCategory(String category){
        for (int i = 0; i < this.categoriesByUserSize(); i++) {
            if (category.equals(this.categoriesUser.get(i))){
                return true;
            }
        }
        return false;
    }

    public void addCategories(String category){
        this.categoriesUser.add(category);
    }

    public void deleteCategories(int id){
        this.categoriesUser.remove(id - 1);
    }
}
