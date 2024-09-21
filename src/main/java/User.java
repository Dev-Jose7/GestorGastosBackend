import java.util.ArrayList;

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

    private static int userCounter;
    private static ArrayList<User> datos = new ArrayList<>();
    public Transaction selectTransaction;
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

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static Boolean validateUser(String email, String password){
        for (int i = 0; i < datos.size(); i++) {
            if(email.equals(datos.get(i).email) && password.equals(datos.get(i).password)){
                System.out.println("Acceso autorizado.");
                System.out.println("Bienvenido " + datos.get(i).name);
                Main.dashboard(datos.get(i));
                return true;
            }
        }
        return false;
    }

    public double sumaIngresos(){
        double total = 0;
        for (int i = 0; i < this.transactions.getListUser().size(); i++) {
            if(this.transactions.getListUser().get(i).getType().equals("Ingreso")){
                total += this.transactions.getListUser().get(i).getValue();
            }
        }
        return total;
    }

    public double sumaGastos(){
        double total = 0;
        for (int i = 0; i < this.transactions.getListUser().size(); i++) {
            if(this.transactions.getListUser().get(i).getType().equals("Gasto")){
                total += this.transactions.getListUser().get(i).getValue();
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

    public void logout(){
        Main.menu();
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