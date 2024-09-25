package Classes;

public class Ingreso extends Transaction{
    private static String type = "Ingreso";

    public Ingreso(int user, int value, String description, String category){
        super(user, Ingreso.type, value, description, category);
    }
}
