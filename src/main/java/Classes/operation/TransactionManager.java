package Classes.operation;

import java.util.ArrayList;

public class TransactionManager extends Transaction{
    private final Transaction transactions;
    private Transaction targetTransaction;

    public TransactionManager(Transaction transactions){
        this.transactions = transactions;
    }

    public int getTotalIngreso(){
        return this.transactions.getListIngreso().size();
    }

    public int getTotalGasto(){
        return this.transactions.getListGasto().size();
    }

    public Transaction getTargetTransaction(){
        return targetTransaction;
    }

    public void createTransaction(int id, String type, int value, String description, String category){
        Transaction transaction = null;
        if(type.equals("Ingreso")){
            transaction = new Ingreso(id, value, description, category);
        } else if(type.equals("Gasto")){
            transaction = new Gasto(id, value, description, category);
        }

        addTransaction(transaction);
//        Transaction.printData();
    }

    private void addTransaction(Transaction transaction){
        this.transactions.getListUser().add(transaction);
        Transaction.getTransactionsData().add(transaction);
//        updateListType();
    }

    public void selectTransaction(int option, ArrayList<Transaction> database){
        targetTransaction = database.get(option - 1);
    }

    public void updateTransaction(String type, int value, String description, String category) {
        if (!targetTransaction.getType().equals(type)) {//Valida si la transaccion cambia de tipo para añadirla al arrayList correspondiente y eliminarla de su anterior ArrayList
            if (type.equals("Ingreso")) {
                this.transactions.getListIngreso().add(targetTransaction);

                for (int i = 0; i < this.getListGasto().size(); i++) {
                    if (targetTransaction == this.transactions.getListGasto().get(i)) {
                        this.transactions.getListGasto().remove(i);
                    }
                }
            } else if (type.equals("Gasto")) {
                this.transactions.getListGasto().add(targetTransaction);

                for (int i = 0; i < this.getListIngreso().size(); i++) {
                    if (targetTransaction == this.transactions.getListIngreso().get(i)) {
                        this.transactions.getListIngreso().remove(i);
                    }
                }
            }

            this.targetTransaction.setType(type); //Usa herencia
        }

        //Si el tipo de la transacción no cambia solo se procede ajustar los demás valores
        //Usan herencia
        this.targetTransaction.setValue(value);
        this.targetTransaction.setCategory(description);
        this.targetTransaction.setCategory(category);
//        Transaction.printData();
    }

    public void deleteTransaction(){
        for (int i = 0; i < this.transactions.getListUser().size(); i++) {
            if(targetTransaction == this.transactions.getListUser().get(i)){
                this.transactions.getListUser().remove(i);
                break;
            }
        }

        for (int i = 0; i < Transaction.getTransactionsData().size(); i++) {
            if(targetTransaction == Transaction.getTransactionsData().get(i)){
                Transaction.getTransactionsData().remove(i);
                break;
            }
        }
//        Transaction.printData();
    }

    public void updateListUser(int idUser){
        if(!this.transactions.getListUser().isEmpty()){
            this.transactions.getListUser().clear();
        }

        for (int i = 0; i < Transaction.getTransactionsData().size(); i++) {
            if(idUser == Transaction.getTransactionsData().get(i).getUser()){
                this.transactions.getListUser().add(Transaction.getTransactionsData().get(i));
            }
        }
    }

    public void updateListType(){
        if(!this.transactions.getListIngreso().isEmpty() || !this.transactions.getListGasto().isEmpty()){
            this.transactions.getListIngreso().clear();
            this.transactions.getListGasto().clear();
        }

        for (int i = 0; i < this.transactions.getListUser().size(); i++) {
            if(this.transactions.getListUser().get(i).getType().equals("Ingreso")){
                this.transactions.getListIngreso().add(this.transactions.getListUser().get(i));

            }else if (this.transactions.getListUser().get(i).getType().equals("Gasto")){
                this.transactions.getListGasto().add(this.transactions.getListUser().get(i));
            }
        }
    }

    public void printDatabase(ArrayList<Transaction> databaseTransaction){
        System.out.println("");
        for (int i = 0; i < databaseTransaction.size(); i++) {
            System.out.println(i+1 + ". " + databaseTransaction.get(i));
        }
        System.out.println("");
    }

    public void printListIngresos(){
        this.transactions.getListIngreso().forEach(System.out::println);
    }

    public void printListGastos(){
        this.transactions.getListGasto().forEach(System.out::println);
    }
}
