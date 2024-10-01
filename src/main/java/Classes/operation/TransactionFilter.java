package Classes.operation;

import java.util.ArrayList;

public class TransactionFilter extends Transaction{
    private final Transaction transactions;
    private String[] dataFilter = new String[5];

    public TransactionFilter(Transaction transactions){
        this.transactions = transactions;
    }

    public String[] getDataFilter(){
        return this.dataFilter;
    }

    public void filter(ArrayList<Transaction> databaseTransactions){
        this.transactions.getListFilter().clear();
        System.out.println(databaseTransactions.size());

//        if(dataFilter[1].equals("0")){
//            dataFilter[1] = "";
//        }

        if(dataFilter[2].equals("0")){
            dataFilter[2] = "";
        }

        for (int i = 0; i < databaseTransactions.size(); i++) {
            boolean status = true;

            if(!dataFilter[0].isEmpty()){
                if(!databaseTransactions.get(i).getType().equals(dataFilter[0])){
                    status = false;
                }
            }

            if (!dataFilter[1].isEmpty()) { //Si hay datos
                try {
                    if(dataFilter[2].isEmpty()){ //Si no hay datos
                        if (this.transactions.getListUser().get(i).getValue() != Integer.parseInt(dataFilter[1])) {
                            status = false;
                        }
                    }else if (this.transactions.getListUser().get(i).getValue() < Integer.parseInt(dataFilter[1])) {
                        status = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("El valor en el filtro no es un número válido");
                    status = false;
                }
            }

            if (!dataFilter[2].isEmpty()) {
                try {
                    if(dataFilter[1].isEmpty()){
                        if (this.transactions.getListUser().get(i).getValue() != Integer.parseInt(dataFilter[2])){
                            status = false;
                        }
                    } else if (this.transactions.getListUser().get(i).getValue() > Integer.parseInt(dataFilter[2])){
                        status = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("El valor en el filtro no es un número válido");
                    status = false;
                }
            }

            if (!dataFilter[3].isEmpty()) {
                if (!this.transactions.getListUser().get(i).getCategory().equals(dataFilter[3])){
                    status = false;
                }
            }

            if (!dataFilter[4].isEmpty()) {
                if (!this.transactions.getListUser().get(i).getDate().equals(dataFilter[4])){
                    status = false;
                }
            }

            if (status) {
                this.transactions.getListFilter().add(this.transactions.getListUser().get(i));
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
        for (int i = 0; i < this.transactions.getListFilter().size(); i++) {
            System.out.println(i+1 + ". " + this.transactions.getListFilter().get(i));
        }
    }
}
