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

    private Transaction targetTransaction;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Transaction> filterTransactions = new ArrayList<>();
    private ArrayList<Transaction> ingresos = new ArrayList<Transaction>();
    private ArrayList<Transaction> gastos = new ArrayList<Transaction>();
    private String[] dataFilter = new String[4];

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

    public String getDate(){
        return this.date;
    }

    public ArrayList<Transaction> getListUser(){
        return transactions;
    }

    public Transaction getTargetTransaction(){
        return targetTransaction;
    }

    public String[] getDataFilter(){
        return this.dataFilter;
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

    private void filterType(Transaction transaction){
        if(transaction.type.equals("Ingreso")){
            this.ingresos.add(transaction);
        }else if(transaction.type.equals("Gasto")){
            this.gastos.add(transaction);
        }
    }

    public void printListUser(){
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(i+1 + ". " + transactions.get(i));
        }
    }

    public void printListIngresos(){
        this.ingresos.forEach(System.out::println);
    }

    public void printListGastos(){
        this.gastos.forEach(System.out::println);
    }

    public int getTotalIngreso(){
        return this.ingresos.size();
    }

    public int getTotalGasto(){
        return this.gastos.size();
    }

    public void createTransaction(int id, String type, int value, String description, String category){
        Transaction transaction = null;
        if(type.equals("Ingreso")){
            transaction = new Ingreso(id, value, description, category);
        } else if(type.equals("Gasto")){
            transaction = new Gasto(id, value, description, category);
        }

        addTransaction(transaction);
        printData();
    }

    private void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        Transaction.transactionsData.add(transaction);
        filterType(transaction);
    }

    public void selectTransaction(String type, int option){
        if(type.equals("allList")){
            targetTransaction = transactions.get(option - 1);
        } else if(type.equals("filterList")){
            targetTransaction = filterTransactions.get(option - 1);
        }
    }

    public void updateTransaction(String type, int value, String description, String category) {
        if (targetTransaction.type != type) {
            if (type == "Ingreso") {
                ingresos.add(targetTransaction);

                for (int i = 0; i < gastos.size(); i++) {
                    if (targetTransaction == gastos.get(i)) {
                        gastos.remove(i);
                    }
                }
            } else if (type == "Gasto") {
                gastos.add(targetTransaction);

                for (int i = 0; i < ingresos.size(); i++) {
                    if (targetTransaction == ingresos.get(i)) {
                        ingresos.remove(i);
                    }
                }
            }
        }

        this.targetTransaction.type = type;
        this.targetTransaction.value = value;
        this.targetTransaction.description = description;
        this.targetTransaction.category = category;

        printData();
    }

    public void deleteTransaction(){
        for (int i = 0; i < ingresos.size(); i++) {
            if(targetTransaction == ingresos.get(i)){
                ingresos.remove(i);
                break;
            }
        }

        for (int i = 0; i < gastos.size(); i++) {
            if(targetTransaction == gastos.get(i)){
                gastos.remove(i);
                break;
            }
        }

        for (int i = 0; i < transactions.size(); i++) {
            if(targetTransaction == transactions.get(i)){
                transactions.remove(i);
                break;
            }
        }

        printData();
    }

    private static void printData(){
        System.out.println("");
        System.out.println("BASE DE DATOS");
        System.out.println("=======================================================================================================");
        Transaction.transactionsData.forEach(System.out::println);
        System.out.println("=======================================================================================================");
        System.out.println("");
    }

    public void filter(){
        filterTransactions.clear();

        for (int i = 0; i < this.transactions.size(); i++) {
            boolean status = true;

            if(!dataFilter[0].isEmpty()){
                if(!transactions.get(i).type.equals(dataFilter[0])){
                    status = false;
                }
            }

            if (!dataFilter[1].isEmpty()) {
                try {
                    if (transactions.get(i).value != Integer.parseInt(dataFilter[1])) {
                        status = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El valor en el filtro no es un número válido");
                    status = false;
                }
            }

            if (!dataFilter[2].isEmpty()) {
                if (!transactions.get(i).category.equals(dataFilter[2])){
                    status = false;
                }
            }

            if (!dataFilter[3].isEmpty()) {
                if (!transactions.get(i).date.equals(dataFilter[3])){
                    status = false;
                }
            }

            if (status) {
                filterTransactions.add(transactions.get(i));
            }
        }
    }

    public void emptyDatafilter(){
        for (int i = 0; i < dataFilter.length; i++) {
            if(dataFilter[i] == null){
                dataFilter[i] = "";
            }
        }
    }

    public void clearDataFilter(){
        for (int i = 0; i < dataFilter.length; i++) {
            if(!dataFilter[i].isEmpty()){
                dataFilter[i] = "";
            }
        }
    }

    public void printListFilter() {
        for (int i = 0; i < filterTransactions.size(); i++) {
            System.out.println(i+1 + ". " + filterTransactions.get(i));
        }
    }

    public ArrayList<Transaction> getListFilter() {
        return this.filterTransactions;
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
