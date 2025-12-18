package controller;

import models.Staff;
import models.enums.Department;
import models.enums.UserType;
import repository.StaffRepository;

import javax.crypto.spec.DESedeKeySpec;

public class StaffController {
    public static boolean addStaff(String password, String name, int umur, String email, String phone, String address, double salary, Department department) {
        if(department == Department.ADMIN) {
            return StaffRepository.addStaff(password, name, umur, email, phone, address, UserType.ADMIN, salary, department);
        }
        return StaffRepository.addStaff(password, name, umur, email, phone, address, UserType.STAFF, salary, department);
    }

    public static boolean updateStaffData(double salary, Department department, String userId) {
        return StaffRepository.updateStaffData(salary, department, userId);
    }

    public static Staff getStaffByEmployeeId(String employeeId){
        return StaffRepository.findStaffByStaffId(employeeId);
    }
}
