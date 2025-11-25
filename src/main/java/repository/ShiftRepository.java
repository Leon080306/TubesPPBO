package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import models.Shift;
import utils.Database;

public class ShiftRepository {
    public static Connection conn = Database.connect();
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
                Shift shift = new Shift(shiftId, startTime, endTime, date, isPresent);
                shiftEmployeeId.add(shift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shiftEmployeeId;
    }

    //show shift untuk semua staff
    public List<Shift> showAllShift(){
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
                Shift shift = new Shift(shiftId, startTime, endTime, date, isPresent);
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
}
