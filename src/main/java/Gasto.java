public class Gasto extends Transaction{
    private static String type = "Gasto";

    public Gasto(int user, double value, String description, String category){
        super(user, Gasto.type, value, description, category);
        super.gastos.add(this);
    }
}
