package moduls;

import controller.UserController;
import models.Users;

public class GlobalVariables {
    private static Users user;

    public static Users getUser(){
        return user;
    }

    public static void setUser(Users userData){
        user = userData;
    }

    public static void refreshUserData() {
        user = UserController.getUserDataByUserId(user.getUserID());
    }
}
