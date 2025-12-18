package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import models.Shift;
import models.Staff;
import models.enums.Department;
import utils.Database;
import utils.GenerateUUID;

public class ShiftRepository {
    public static Connection conn = Database.connect();

    //add shift untuk staff (admin)
    public boolean addShift(String employeeId, LocalTime startTime, LocalTime endTime, LocalDate date){
        String sql = "INSERT into shift(shiftid, employeeid, starttime, endtime, date, ispresent) VALUES (?,?,?,?,?,'false')";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, GenerateUUID.generateUUID());
            pstmt.setString(2, employeeId);
            pstmt.setTime(3, Time.valueOf(startTime));
            pstmt.setTime(4, Time.valueOf(endTime));
            pstmt.setDate(5, Date.valueOf(date));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //show shift untuk satu staff
    public List<Shift> findShiftsByEmployeeId(String employeeId){
        List<Shift> shiftEmployeeId = new ArrayList<>();
        String sql = "SELECT shift.*, u.nama, staff.employeeid, staff.department FROM users u INNER JOIN staff ON u.userid = staff.userid INNER JOIN shift ON staff.employeeid = shift.employeeid WHERE shift.employeeid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, employeeId);
            ResultSet resultQuery = pstmt.executeQuery();
            while (resultQuery.next()) {
                String shiftId = resultQuery.getString("shiftid");
                LocalTime startTime = resultQuery.getTime("starttime").toLocalTime();
                LocalTime endTime = resultQuery.getTime("endtime").toLocalTime();
                LocalDate date = resultQuery.getDate("date").toLocalDate();
                Boolean isPresent = resultQuery.getBoolean("ispresent");
                String staffId = resultQuery.getString("employeeid");
                Shift shift = new Shift(shiftId, startTime, endTime, date, isPresent, staffId);
                shiftEmployeeId.add(shift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shiftEmployeeId;
    }

    //show all shift perhari (admin)
    public List<Shift> getAllShiftsByDate(LocalDate dateInput){
        
        List<Shift> listShiftStaff = new ArrayList<>();
        String sql = "SELECT shift.*, u.nama, staff.employeeid, staff.department FROM shift INNER JOIN staff ON shift.employeeid = staff.employeeid INNER JOIN users u ON staff.userid = u.userid WHERE shift.date = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(dateInput));
            ResultSet resultQuery = pstmt.executeQuery();
            
            while (resultQuery.next()) {
                String shiftId = resultQuery.getString("shiftid");
                LocalTime startTime = resultQuery.getTime("starttime").toLocalTime();
                LocalTime endTime = resultQuery.getTime("endtime").toLocalTime();
                LocalDate date = resultQuery.getDate("date").toLocalDate();
                Boolean isPresent = resultQuery.getBoolean("ispresent"); 
                String staffId = resultQuery.getString("employeeid");                   
                Shift shift = new Shift(shiftId, startTime, endTime, date, isPresent, staffId);
                listShiftStaff.add(shift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listShiftStaff;
    }

    //show shift untuk semua staff
    public List<Shift> showAllShifts(){
        List<Shift> listShiftStaff = new ArrayList<>();
        String sql = "SELECT shift.*, u.nama, staff.employeeid, staff.department FROM shift INNER JOIN staff ON shift.employeeid = staff.employeeid INNER JOIN users u ON staff.userid = u.userid";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultQuery = pstmt.executeQuery();
            
            while (resultQuery.next()) {
                String shiftId = resultQuery.getString("shiftid");
                LocalTime startTime = resultQuery.getTime("starttime").toLocalTime();
                LocalTime endTime = resultQuery.getTime("endtime").toLocalTime();
                LocalDate date = resultQuery.getDate("date").toLocalDate();
                Boolean isPresent = resultQuery.getBoolean("ispresent");   
                String staffId = resultQuery.getString("employeeid")  ;               
                Shift shift = new Shift(shiftId, startTime, endTime, date, isPresent, staffId);
                listShiftStaff.add(shift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listShiftStaff;
    }

    //presensi
    public boolean submitAttendance(String employeeId, String shiftId, boolean isPresent){
        String sql = "UPDATE shift SET ispresent = ? WHERE employeeid = ? AND shiftid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, isPresent);
            pstmt.setString(2, employeeId);
            pstmt.setString(3, shiftId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //get shift id 1 staff by department
    public List<Shift> getShiftByDepartment(Department department){
        String sqlGetShiftId = "SELECT st.*, t.status FROM task t INNER JOIN shift sh ON sh.shiftid = t.shiftid INNER JOIN staff st ON st.employeeid = sh.employeeid WHERE t.status != 'ON_PROGRESS' AND t.status != 'ASSIGNED' AND st.department = cast(? as department_type)";
        List<Shift> availableShift = new ArrayList<>();

        try {
            PreparedStatement pstmtGetStaff = conn.prepareStatement(sqlGetShiftId);
            pstmtGetStaff.setString(1, department.name());
            ResultSet resultQuery = pstmtGetStaff.executeQuery();
            while (resultQuery.next()) {
                availableShift.addAll(findShiftsByEmployeeId("employeeid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableShift;
        
    }
}
