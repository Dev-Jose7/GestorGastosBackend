package Classes;

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




    private static int counterTransaction;
    private static ArrayList<Transaction> transactionsData = new ArrayList<Transaction>();

    public Transaction(){}

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


    public static void printData(){
        System.out.println("");
        System.out.println("BASE DE DATOS");
        System.out.println("=======================================================================================================");
        Transaction.transactionsData.forEach(System.out::println);
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
