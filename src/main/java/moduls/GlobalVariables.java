package moduls;

import models.Users;

public class GlobalVariables {
    private static Users user;

    public Users getUser(){
        return this.user;
    }

    public void setUser(Users user){
        this.user = user;
    }
}
