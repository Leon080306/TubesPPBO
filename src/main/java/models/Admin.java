package models;

import models.enums.Department;
import models.enums.UserType;

public class Admin extends Staff {
    public Admin (String userId, String password, String nama, int umur, String email, String phone, String address, String employeeID, double salary) {
        super(userId, password, nama, umur, email, phone, address, employeeID, salary, Department.ADMIN, UserType.ADMIN);
    }
}