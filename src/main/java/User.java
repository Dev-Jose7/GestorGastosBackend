import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String type;
    private String bank;
    private double balance;
    private Category catogories;

    private static int userCounter;
    public static ArrayList<User> datos = new ArrayList<>();
    public Transaction selectTransaction;
    private String selectCategory;
    //Atributos static: Se comparten entre todas las instancias. Solo existe una copia de la variable para toda la clase, por lo que las listas es ideal que solo exista una copia ya que aquí se guardarán a todos los usuarios
    //Métodos static: Pueden ser llamados sin necesidad de crear una instancia de la clase.

    public ArrayList<Transaction> ingresoUser = new ArrayList<Transaction>();
    public ArrayList<Transaction> gastoUser = new ArrayList<Transaction>();

    public User(String name, String email, String password) {//CreateUser - Constructor
        this.id = ++userCounter;
        this.name = name;
        this.email = email;
        this.password = password;
        this.catogories = new Category();
        datos.add(this);
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

    public int getTotalIngreso(){
        return ingresoUser.size();
    }

    public int getTotalGasto(){
        return gastoUser.size();
    }

    public Category getCatogories(){
        return this.catogories;
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
        loadIngreso();

        double total = 0;
        for (int i = 0; i < ingresoUser.size(); i++) {
            total += ingresoUser.get(i).getValue();
        }
        return total;
    }

    public double sumaGastos(){
        loadGasto();

        double total = 0;
        for (int i = 0; i < gastoUser.size(); i++) {
            total += gastoUser.get(i).getValue();
        }
        return total;
    }

    public double sumaTotal() {
        double ingreso = sumaIngresos();
        double gasto = sumaGastos();
        double total = ingreso - gasto;
        return total;
    }

    public void loadIngreso(){
        Transaction.userIngreso(this.id, this.ingresoUser);
    }

    public void loadGasto(){
        Transaction.userGasto(this.id, this.gastoUser);
    }

    public void printIngreso(){
        this.ingresoUser.forEach(System.out::println);
    }

    public void printGasto(){
        this.gastoUser.forEach(System.out::println);
    }

    public void updateUser(){

    };

    public void createTransaction(String type, double value, String description, String category){
        if(type.equals("Ingreso")){
            Ingreso transaction = new Ingreso(this.id, value, description, category);
        } else if(type.equals("Gasto")){
            Gasto transaction = new Gasto(this.id, value, description, category);
        }

        Transaction.printData();
    }

    public ArrayList<Transaction> getTransactionOnList(){ //Permite tener todas las transacciones almacenadas de ingresoUser y gastoUser
        ArrayList<Transaction> transaction = new ArrayList<Transaction>();

        for (int i = 0; i < ingresoUser.size(); i++) {
            transaction.add(ingresoUser.get(i));
        }

        for (int i = 0; i < gastoUser.size(); i++) {
            transaction.add(gastoUser.get(i));
        }

        return transaction;
    }

    public void printTransactionOnList(){
        ArrayList<Transaction> transactionList = getTransactionOnList();
        for (int i = 0; i < transactionList.size(); i++) {
            System.out.println(i+1 + ". " + transactionList.get(i));
        }
    }

    public void takeTransaction(int option){ //Obtiene la transacción especifica de la lista en donde se guardarán todas las transacciones del usuario para seguidamente buscar esta transacción seleccionada desde la base de datos de la transacciones
        ArrayList<Transaction> transactionList = getTransactionOnList();

        Transaction transaction = transactionList.get(option - 1);

        for (int i = 0; i < Transaction.ingresos.size(); i++) {
            if(transaction == Transaction.ingresos.get(i)){
                selectTransaction = Transaction.ingresos.get(i);
            }
        }

        for (int i = 0; i < Transaction.gastos.size(); i++) {
            if(transaction == Transaction.gastos.get(i)){
                selectTransaction = Transaction.gastos.get(i);
            }
        }

    }

    public void updateTransaction(String type, int value, String description, String category){
        if(selectTransaction.getType() != type){
            if(type == "Ingreso"){
                Transaction.ingresos.add(selectTransaction);

                for (int i = 0; i < Transaction.gastos.size(); i++) {
                    if(selectTransaction == Transaction.gastos.get(i)){
                        Transaction.gastos.remove(i);
                    }
                }
            }else if(type == "Gasto"){
                Transaction.gastos.add(selectTransaction);

                for (int i = 0; i < Transaction.ingresos.size(); i++) {
                    if(selectTransaction == Transaction.ingresos.get(i)){
                        Transaction.ingresos.remove(i);
                    }
                }
            }
        }

        selectTransaction.setType(type);
        selectTransaction.setValue(value);
        selectTransaction.setDescription(description);
        selectTransaction.setCategory(category);

        Transaction.printData();
    }

    public void deleteTransaction(){
        for (int i = 0; i < Transaction.ingresos.size(); i++) {
            if(selectTransaction == Transaction.ingresos.get(i)){
                Transaction.ingresos.remove(i);
                break;
            }
        }

        for (int i = 0; i < Transaction.gastos.size(); i++) {
            if(selectTransaction == Transaction.gastos.get(i)){
                Transaction.gastos.remove(i);
                break;
            }
        }

        Transaction.printData();
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