package Classes;

import java.util.ArrayList;

public class TransactionManager {
    private Transaction targetTransaction;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Transaction> ingresos = new ArrayList<Transaction>();
    private ArrayList<Transaction> gastos = new ArrayList<Transaction>();
    private TransactionFilter transactionFilter;

    public TransactionManager(){
        this.transactionFilter = new TransactionFilter(this.transactions);
    }

    public TransactionFilter getTransactionFilter() {
        return transactionFilter;
    }

    public int getTotalIngreso(){
        return this.ingresos.size();
    }

    public int getTotalGasto(){
        return this.gastos.size();
    }

    public Transaction getTargetTransaction(){
        return targetTransaction;
    }

    public ArrayList<Transaction> getListUser(){
        return transactions;
    }

    public ArrayList<Transaction> getTransactions(){
        return this.transactions;
    }

    public void createTransaction(int id, String type, int value, String description, String category){
        Transaction transaction = null;
        if(type.equals("Ingreso")){
            transaction = new Ingreso(id, value, description, category);
        } else if(type.equals("Gasto")){
            transaction = new Gasto(id, value, description, category);
        }

        addTransaction(transaction);
        Transaction.printData();
    }

    private void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        Transaction.getTransactionsData().add(transaction);
        filterType(transaction);
    }

    public void selectTransaction(String type, int option){
        if(type.equals("allList")){
            targetTransaction = transactions.get(option - 1);
        } else if(type.equals("filterList")){
            targetTransaction = this.transactionFilter.getListFilter().get(option - 1);
        }
    }

    public void updateTransaction(String type, int value, String description, String category) {
        if (!targetTransaction.getType().equals(type)) {//Valida si la transaccion cambia de tipo para añadirla al arrayList correspondiente y eliminarla de su anterior ArrayList
            if (type.equals("Ingreso")) {
                ingresos.add(targetTransaction);

                for (int i = 0; i < gastos.size(); i++) {
                    if (targetTransaction == gastos.get(i)) {
                        gastos.remove(i);
                    }
                }
            } else if (type.equals("Gasto")) {
                gastos.add(targetTransaction);

                for (int i = 0; i < ingresos.size(); i++) {
                    if (targetTransaction == ingresos.get(i)) {
                        ingresos.remove(i);
                    }
                }
            }

            this.targetTransaction.setType(type);
        }

        //Si el tipo de la transacción no cambia solo se procede ajustar los demás valores
        this.targetTransaction.setValue(value);
        this.targetTransaction.setCategory(description);
        this.targetTransaction.setCategory(category);

        Transaction.printData();
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

        Transaction.printData();
    }

    private void filterType(Transaction transaction){
        if(transaction.getType().equals("Ingreso")){
            this.ingresos.add(transaction);
        }else if(transaction.getType().equals("Gasto")){
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
}
