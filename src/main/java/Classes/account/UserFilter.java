package Classes.account;

public class UserFilter{
    private final User user;
    private String[] dataFilter = new String[3];

    public UserFilter(User user){
        this.user = user;
    }

    public String[] getDataFilter(){
        return this.dataFilter;
    }

    public User getUser() {
        return user;
    }

    public void filter(){
        this.user.getListFilter().clear();
        System.out.println(User.getUserData().size());

        for (int i = 0; i < User.getUserData().size(); i++) {
            boolean status = true;

            if(!dataFilter[0].isEmpty()){
                if(!Integer.toString(User.getUserData().get(i).getId()).equals(dataFilter[0])){
                    status = false;
                }
            }

            if (!dataFilter[1].isEmpty()) {
                if (!User.getUserData().get(i).getName().equals(dataFilter[1])){
                    status = false;
                }
            }

            if (!dataFilter[2].isEmpty()) {
                if (!User.getUserData().get(i).getEmail().equals(dataFilter[2])){
                    status = false;
                }
            }

            if (status) {
                this.user.getListFilter().add(User.getUserData().get(i));
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
        for (int i = 0; i < this.user.getListFilter().size(); i++) {
            System.out.println(i+1 + ". " + this.user.getListFilter().get(i));
        }
    }
}
