package classes.account;

import java.util.ArrayList;

public class UserManager{
    private final User user;
    private User targetUser;

    public UserManager(User user){
        this.user = user;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void selectUser(int option, ArrayList<User> databaseUsers){
        this.targetUser = databaseUsers.get(option - 1);
    }

    public void deleteUser() {
        for (int i = 0; i < User.getUserData().size(); i++) {
            if(this.targetUser == User.getUserData().get(i)){
                User.getUserData().remove(i);
                break;
            }
        }
    }

    public void printDatabase(ArrayList<User> databaseUser){
        for (int i = 0; i < databaseUser.size(); i++) {
            System.out.println(i+1 + ". " + databaseUser.get(i));
        }
    }
}
