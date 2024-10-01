package Classes.account;

import Classes.operation.Transaction;
import Menu.main.Index;

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

    public void selectTransaction(int option, ArrayList<User> databaseUsers){
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
}
