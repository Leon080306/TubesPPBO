package controller;

import models.Staff;
import repository.StaffRepository;

public class StaffController {
    StaffRepository staffRepository;

    public StaffController(){
        staffRepository = new StaffRepository();
    }

    public Staff getStaffByEmployeeId(String employeeId){
        return staffRepository.findStaffByStaffId(employeeId);
    }
}
