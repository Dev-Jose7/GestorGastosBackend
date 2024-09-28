package Classes;

import java.util.ArrayList;
import Menu.Dashboard;
import Menu.Index;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String type;
    private String bank;
    private double balance;
    private Transaction transactions;
    private Category catogories;
    private TransactionManager transactionManager;

    private static int userCounter;
    private static ArrayList<User> datos = new ArrayList<>();
    private String selectCategory;
    //Atributos static: Se comparten entre todas las instancias. Solo existe una copia de la variable para toda la clase, por lo que las listas es ideal que solo exista una copia ya que aquí se guardarán a todos los usuarios
    //Métodos static: Pueden ser llamados sin necesidad de crear una instancia de la clase.

    public User(String name, String email, String password) {//CreateUser - Constructor
        this.id = ++userCounter;
        this.name = name;
        this.email = email;
        this.password = password;
        this.transactions = new Transaction();
        this.catogories = new Category();
        this.transactionManager = new TransactionManager();
        datos.add(this);
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Category getCatogories(){
        return this.catogories;
    }

    public Transaction getTransactions(){
        return this.transactions;
    }

    public TransactionManager getTransactionManager(){
        return this.transactionManager;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static boolean validateUser(String email, String password){
        for (int i = 0; i < datos.size(); i++) {
            if(email.equals(datos.get(i).email) && password.equals(datos.get(i).password)){
                System.out.println("Acceso autorizado.");
                System.out.println("Bienvenido " + datos.get(i).name);
                Dashboard.menu(datos.get(i));
                System.out.println("");
                return true;
            }
        }
        return false;
    }

    public double sumaIngresos(){
        double total = 0;
        for (int i = 0; i < this.transactionManager.getListUser().size(); i++) {
            if(this.transactionManager.getListUser().get(i).getType().equals("Ingreso")){
                total += this.transactionManager.getListUser().get(i).getValue();
            }
        }
        return total;
    }

    public double sumaGastos(){
        double total = 0;
        for (int i = 0; i < this.transactionManager.getListUser().size(); i++) {
            if(this.transactionManager.getListUser().get(i).getType().equals("Gasto")){
                total += this.transactionManager.getListUser().get(i).getValue();
            }
        }
        return total;
    }

    public double sumaTotal() {
        double ingreso = sumaIngresos();
        double gasto = sumaGastos();
        double total = ingreso - gasto;
        return total;
    }

    public void logout(User user){
        user = null;
        Index.main();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}