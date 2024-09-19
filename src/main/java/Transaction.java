import java.util.ArrayList;

public class Transaction{
    private int id;
    private int user;
    private String type;
    private double value;
    private String description;
    private String category;
    private String date;

    private static int counterTransaction;

    public static ArrayList<Transaction> ingresos = new ArrayList<Transaction>();
    public static ArrayList<Transaction> gastos = new ArrayList<Transaction>();

    public Transaction(int user, String type, double value, String description, String category){
        this.id = ++counterTransaction;
        this.user = user;
        this.type = type;
        this.value = value;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public String getType(){
        return this.type;
    }

    public double getValue(){
        return this.value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static void userIngreso(int id, ArrayList<Transaction> ingresoUser) {
        ingresoUser.clear();
        for (int i = 0; i < ingresos.size(); i++) {
            if(id == ingresos.get(i).user){
                ingresoUser.add( ingresos.get(i));
            }
        }
    }

    public static void userGasto(int id, ArrayList<Transaction> gastoUser) {
        gastoUser.clear();
        for (int i = 0; i < gastos.size(); i++) {
            if(id == gastos.get(i).user){
                gastoUser.add( gastos.get(i));
            }
        }
    }

    public static void printData(){
        System.out.println("");
        System.out.println("BASE DE DATOS");
        System.out.println("=======================================================================================================");
        System.out.println("TRANSACCIONES INGRESO");
        ingresos.forEach(System.out::println);
        System.out.println("=======================================================================================================");

        System.out.println("=======================================================================================================");
        System.out.println("TRANSACCIONES GASTO");
        gastos.forEach(System.out::println);
        System.out.println("=======================================================================================================");
        System.out.println("");
    }

    @Override
    public String toString() {
        return  "[" +
                "id=" + id +
                ", user=" + user +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                ']';
    }
}
