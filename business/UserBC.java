package edu.umiami.caneeats.business;

import edu.umiami.caneeats.database.UserDataManager;
import edu.umiami.caneeats.entityobjects.Restaurant;
import edu.umiami.caneeats.entityobjects.User;

import java.util.Set;

public class UserBC {

    private UserDataManager rdm ;

    public UserBC()
    {
        this.rdm = new UserDataManager();
    }

    public Set<User> getUsersList(String r) {
        return rdm.getUsersList(r);
    }

    public int addUser(User r) {
        return rdm.addNewUser(r);
    }

    public int modifyUser(User r){
        return rdm.updateUser(r);
    }

    public int deleteUser(int id){
        return rdm.deleteUser(id);
    }
}
