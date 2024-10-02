package Classes.operation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Transaction{
    private int id;
    private int user;
    private String type;
    private int value;
    private String description;
    private String category;
    private String date;
    private String hour;

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Transaction> ingresos = new ArrayList<Transaction>();
    private ArrayList<Transaction> gastos = new ArrayList<Transaction>();
    private ArrayList<Transaction> filters = new ArrayList<>();

    private static int counterTransaction;
    private static ArrayList<Transaction> transactionsData = new ArrayList<Transaction>();

    public Transaction() {}

    public Transaction(int user, String type, int value, String description, String category){
        this.id = ++counterTransaction;
        this.user = user;
        this.type = type;
        this.value = value;
        this.description = description;
        this.category = category;
        this.date = stringDate();
    }

    public int getId() {
        return this.id;
    }

    public int getUser(){
        return this.user;
    }

    public String getType(){
        return this.type;
    }

    public double getValue(){
        return this.value;
    }

    public String getCategory(){
        return this.category;
    }

    public String getDate(){
        return this.date;
    }

    public ArrayList<Transaction> getListUser(){
        return transactions;
    }

    public ArrayList<Transaction> getListIngreso(){
        return this.ingresos;
    }

    public ArrayList<Transaction> getListGasto(){
        return this.gastos;
    }

    public ArrayList<Transaction> getListFilter(){
        return this.filters;
    }

    public static ArrayList<Transaction> getTransactionsData() {
        return transactionsData;
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

    private String stringDate(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(format);
    }

    public static int totalTransactions(){
        return transactionsData.size();
    }

    public static void printData(){
        System.out.println("");
        System.out.println("BASE DE DATOS - TRANSACCIONES");
        System.out.println("=======================================================================================================");
        for (int i = 0; i < transactionsData.size(); i++) {
            System.out.println(i+1 + ". " + transactionsData.get(i));
        }
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
