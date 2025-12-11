package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import models.Shift;
import repository.ShiftRepository;

public class ShiftController {
    ShiftRepository shiftRepository;

    public ShiftController(){
        shiftRepository = new ShiftRepository();
    }

    public boolean addShift(String employeeId, LocalTime startTime, LocalTime endTime, LocalDate date){
        if (shiftRepository.addShift(employeeId, startTime, endTime, date)) {
            return true;
        }
        return false;
    }

    public List<Shift> getAllShifts(){
        return shiftRepository.showAllShift();
    }
    public List<Shift> getShiftsByEmployeeId(String employeeId){
        return shiftRepository.findShiftsByEmployeeId(employeeId);
    }

    public boolean submitAttendance(String employeeId, String shiftId, boolean isPresent){
        if (shiftRepository.submitAttendance(employeeId, shiftId, isPresent)) {
            return true;
        }
        return false;
    }

    public List<Shift> getDailyAttendanceReport(LocalDate dateInput){
        return shiftRepository.getAllShiftByDate(dateInput);
    }
}
