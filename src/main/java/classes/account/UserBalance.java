package classes.account;

public class UserBalance {
    private User user;

    public UserBalance(User user){
        this.user = user;
    }

    public double sumaIngresos(){
        double total = 0;
        for (int i = 0; i < this.user.getTransactions().getListUser().size(); i++) {
            if(this.user.getTransactions().getListUser().get(i).getType().equals("Ingreso")){
                total += this.user.getTransactions().getListUser().get(i).getValue();
            }
        }
        return total;
    }

    public double sumaGastos(){
        double total = 0;
        for (int i = 0; i < this.user.getTransactions().getListUser().size(); i++) {
            if(this.user.getTransactions().getListUser().get(i).getType().equals("Gasto")){
                total += this.user.getTransactions().getListUser().get(i).getValue();
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


}
