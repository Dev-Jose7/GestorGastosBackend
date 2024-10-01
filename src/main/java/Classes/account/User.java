package Classes.account;

import java.util.ArrayList;
import java.util.Calendar;

import Classes.tag.Category;
import Classes.operation.Transaction;
import Classes.operation.TransactionFilter;
import Classes.operation.TransactionManager;
import Menu.panel.Dashboard;
import Menu.main.Index;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String type;
    private String bank;
    private double balance;
    private UserBalance userBalance;
    private UserManager userManager;
    private UserFilter userFilter;
    private Transaction transactions;
    private Category catogories;
    private TransactionManager transactionManager;
    private TransactionFilter transactionFilter;

    private static int userCounter;
    private static ArrayList<User> userData = new ArrayList<>();

    private ArrayList<User> filters = new ArrayList<>();
    private String selectCategory;
    //Atributos static: Se comparten entre todas las instancias. Solo existe una copia de la variable para toda la clase, por lo que las listas es ideal que solo exista una copia ya que aquí se guardarán a todos los usuarios
    //Métodos static: Pueden ser llamados sin necesidad de crear una instancia de la clase.

    public User(String name, String email, String password) {//CreateUser - Constructor
        this.id = ++userCounter;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userBalance = new UserBalance(this);
        if(this.id == 1){
            this.userManager = new UserManager(this);
            this.userFilter = new UserFilter(this);
        }
        this.transactions = new Transaction();
        this.catogories = new Category();
        this.transactionManager = new TransactionManager(this.transactions);
        this.transactionFilter =  new TransactionFilter(this.transactions);
        userData.add(this);
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

    public static ArrayList<User> getUserData(){
        return userData;
    }

    public ArrayList<User> getListFilter() {
        return this.filters;
    }

    public UserBalance getUserBalance(){
        return this.userBalance;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public UserFilter getUserFilter(){
        return this.userFilter;
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

    public TransactionFilter getTransactionFilter(){
        return this.transactionFilter;
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

    public static int totalUsers(){
        return userData.size();
    }

    public static boolean validateUser(String email, String password){
        for (int i = 0; i < userData.size(); i++) {
            if(email.equals(userData.get(i).email) && password.equals(userData.get(i).password)){
                System.out.println("Acceso autorizado.");
                System.out.println("Bienvenido " + userData.get(i).name);
                Dashboard.menu(userData.get(i));
                System.out.println("");
                return true;
            }
        }
        return false;
    }

    public void logout(User user){
        user = null;
        Index.main();
    }

    public void printData(){
        System.out.println("");
        System.out.println("BASE DE DATOS - USUARIOS");
        System.out.println("=======================================================================================================");
        for (int i = 0; i < userData.size(); i++) {
            System.out.println(i+1 + ". " + userData.get(i));
        }
        System.out.println("=======================================================================================================");
        System.out.println("");
    }

    @Override
    public String toString() {
        return "[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}