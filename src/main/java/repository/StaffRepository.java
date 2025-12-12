package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Staff;
import models.enums.Department;
import utils.Database;

public class StaffRepository {
    public static Connection conn = Database.connect();

    public Staff findStaffByStaffId(String staffId){
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
