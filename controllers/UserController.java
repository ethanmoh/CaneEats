package edu.umiami.caneeats.controllers;

import edu.umiami.caneeats.business.UserBC;
import edu.umiami.caneeats.entityobjects.Restaurant;
import edu.umiami.caneeats.entityobjects.User;


import java.util.Set;

public class UserController {

    private UserBC userBC;
    public UserController()
    {
        this.userBC = new UserBC();
    }

    public Set<User> getUsersList(String name) {
        if(name!= null && !name.isBlank())
        {
            return userBC.getUsersList(name);
        }
        else
        {
            return null ;
        }
    }

    public int addUser(User r) {
        return userBC.addUser(r);
    }

    public int modifyUser(User r) {
        return userBC.modifyUser(r);
    }

    public int deleteUser(int id){
        return userBC.deleteUser(id);
    }

}

