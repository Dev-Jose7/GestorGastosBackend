package Classes.operation;

public class Gasto extends Transaction {
    private static String type = "Gasto";

    public Gasto(int user, int value, String description, String category){
        super(user, Gasto.type, value, description, category);
    }
}
