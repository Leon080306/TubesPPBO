package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Staff;
import models.enums.Department;
import models.enums.MembershipLevel;
import models.enums.UserType;
import utils.Database;
import utils.GenerateUUID;

public class StaffRepository {
    public static Connection conn = Database.connect();

    public static boolean addStaff(String password, String name, int umur, String email, String phone, String address, UserType userType, double salary, Department department) {
        try {
            String uuid = UserRepository.addUser(password, name, umur, email, phone, address, userType);
            PreparedStatement insertStaff = conn.prepareStatement("INSERT INTO staff (employeeid, userid, salary, department) VALUES (?, ?, ?, cast(? as department_type))");
            insertStaff.setString(1, GenerateUUID.generateUUID());
            insertStaff.setString(2, uuid);
            insertStaff.setDouble(3, salary);
            insertStaff.setString(4, department.name());
            return insertStaff.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStaffData(double salary, Department department, String userId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE staff SET salary = ?, department = cast(? as department_type) WHERE userid = ?");
            pstmt.setDouble(1, salary);
            pstmt.setString(2, department.name());
            pstmt.setString(3, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Staff findStaffByStaffId(String staffId){
        Staff staff = null;
        String sql = "SELECT st.*, u.userid, u.nama, u.password, u.email, u.umur, u.phone, u.address FROM users u INNER JOIN staff st ON u.userid = st.userid WHERE st.userid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffId);
            ResultSet resultQuery = pstmt.executeQuery();
            
            if (resultQuery.next()) {
                
                String userId = resultQuery.getString("userid");
                String password = resultQuery.getString("password");
                String name = resultQuery.getString("nama");
                int age = resultQuery.getInt("umur");
                String email = resultQuery.getString("email");
                String phone = resultQuery.getString("phone");
                String address = resultQuery.getString("address");
                String employeeId = resultQuery.getString("employeeid");
                double salary = resultQuery.getDouble("salary");
                String departmentString = resultQuery.getString("department");
                Department department = Department.valueOf(departmentString);
    
                staff = new Staff(userId, password, name, age, email, phone, address, employeeId, salary, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }
}
