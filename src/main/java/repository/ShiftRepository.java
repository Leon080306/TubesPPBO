package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import models.Shift;
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
    public List<Shift> getAllShiftByDate(LocalDate dateInput){
        
        List<Shift> listShiftStaff = new ArrayList<>();
        String sql = "SELECT shift.*, u.nama, staff.employeeid, staff.department FROM shift INNER JOIN staff ON shift.employeeid = staff.employeeid INNER JOIN users u ON staff.userid = u.userid WHERE shift.date = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1, Date.valueOf(dateInput));
            ResultSet resultQuery = pstmt.executeQuery();
            
            while (resultQuery.next()) {
                String nama = resultQuery.getString("nama");
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ShiftRepository cobaShift = new ShiftRepository();
       
        // List<Shift> dataShift =  cobaShift.showAllShift();
        // for (Shift s : dataShift) {
        //     System.out.println();
        //     System.out.println(s.getShiftId() + " - " + s.getStartTime() + " - " + s.getEndTime() + " - " + s.getDate() + " - " + s.isPresent() + "\nstaff id: " + s.getStaffId());
        // }

        // System.out.println("Masukan employee id: ");
        // String employeeIdInput = sc.nextLine();
        // dataShift = cobaShift.findShiftsByEmployeeId(employeeIdInput);
        // for (Shift sh : dataShift) {
        //    System.out.println(sh.getShiftId() + " - " + sh.getStartTime() + " - " + sh.getEndTime() + " - " + sh.getDate() + " - " + sh.isPresent());
        // }

        // System.out.println("Masukkan employee id: ");
        // String absenEmployee = sc.nextLine();
        // System.out.println("Masukkan shift id: ");
        // String absenShiftId = sc.nextLine();
        // System.out.println("Apakah hadir (true = hadir or false = tidak hadir) : ");
        // boolean absenKehadiran = sc.nextBoolean();
        // cobaShift.submitAttendance(absenEmployee, absenShiftId, absenKehadiran);

        // System.out.println("Masukkan employee id: ");
        // String employeeIdAdd = sc.nextLine();
        // System.out.println("Masukkan start time shift (HH:mm:ss): ");
        // String startTimeInput = sc.nextLine();
        // LocalTime starTime = LocalTime.parse(startTimeInput, timeFormat);
        // System.out.println("Masukkan end time shift (HH:mm:ss): ");
        // String endTimeInput = sc.nextLine();
        // LocalTime endTime = LocalTime.parse(endTimeInput, timeFormat);
        // System.out.println("Masukkan tanggal shift (yyyy-MM-dd): ");
        // String dateInput = sc.nextLine();
        // LocalDate date = LocalDate.parse(dateInput, dateFormat);
        // if (cobaShift.addShift(employeeIdAdd, starTime, endTime, date)) {
        //     System.out.println("Berhasil");
        // } else {
        //     System.out.println("gagal");
        // }

        System.out.println("Masukkan tanggal presensi (yyyy-MM-dd) : ");
        String dateSearchInput = sc.nextLine();
        LocalDate dateSearch = LocalDate.parse(dateSearchInput, dateFormat);
        List<Shift> getShiftList = cobaShift.getAllShiftByDate(dateSearch);
        for (Shift shift : getShiftList) {
            System.out.println();
            System.out.println(shift.getShiftId() + " - " + shift.getStartTime() + " - " + shift.getEndTime() + " - " + shift.getDate() + " - " + shift.isPresent());
        }
    }
}
