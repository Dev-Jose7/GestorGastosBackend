package Classes;

import java.util.ArrayList;

public class TransactionFilter {

    private String[] dataFilter = new String[5];
    private ArrayList<Transaction> filterTransactions = new ArrayList<>();
    private ArrayList<Transaction> transactions;

    public TransactionFilter(){}

    public TransactionFilter(ArrayList<Transaction> transactions){
        this.transactions = transactions;
    }

    public ArrayList<Transaction> getListFilter() {
        return this.filterTransactions;
    }

    public String[] getDataFilter(){
        return this.dataFilter;
    }

    public void filter(){
        filterTransactions.clear();
        System.out.println(this.transactions);

        if(dataFilter[1].equals("0")){
            dataFilter[1] = "";
        }

        if(dataFilter[2].equals("0")){
            dataFilter[2] = "";
        }

        for (int i = 0; i < this.transactions.size(); i++) {
            boolean status = true;

            if(!dataFilter[0].isEmpty()){
                if(!transactions.get(i).getType().equals(dataFilter[0])){
                    status = false;
                }
            }

            if (!dataFilter[1].isEmpty()) {
                try {
                    if(dataFilter[2].isEmpty()){
                        if (transactions.get(i).getValue() != Integer.parseInt(dataFilter[1])) {
                            status = false;
                        }
                    }else if (transactions.get(i).getValue() < Integer.parseInt(dataFilter[1])) {
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
                        if (transactions.get(i).getValue() != Integer.parseInt(dataFilter[2])){
                            status = false;
                        }
                    } else if (transactions.get(i).getValue() > Integer.parseInt(dataFilter[2])){
                        status = false;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("El valor en el filtro no es un número válido");
                    status = false;
                }
            }

            if (!dataFilter[3].isEmpty()) {
                if (!transactions.get(i).getCategory().equals(dataFilter[3])){
                    status = false;
                }
            }

            if (!dataFilter[4].isEmpty()) {
                if (!transactions.get(i).getDate().equals(dataFilter[4])){
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
}
