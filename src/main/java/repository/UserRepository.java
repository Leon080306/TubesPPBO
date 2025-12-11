package repository;

import exceptions.NoResultsFound;
import models.Admin;
import models.Guest;
import models.Staff;
import models.Users;
import models.enums.Department;
import models.enums.MembershipLevel;
import models.enums.UserType;
import utils.Database;
import utils.GenerateUUID;
import utils.PasswordHashing;
import utils.StringToEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private static final Connection con = Database.connect();

    public static Users getUserData(String email) throws NoResultsFound {
        try {
            PreparedStatement prepUser = con.prepareStatement("SELECT * FROM users WHERE email = ?");
            prepUser.setString(1, email);
            ResultSet userResult = prepUser.executeQuery();
            if(!userResult.next()) {
                throw new NoResultsFound();
            }

            String userId = userResult.getString("userid");
            String password = userResult.getString("password");
            String nama = userResult.getString("nama");
            int umur = userResult.getInt("umur");
            String phone = userResult.getString("phone");
            String address = userResult.getString("address");
            String type = userResult.getString("type");

            if(type.equalsIgnoreCase("GUEST")) {
                PreparedStatement prepGuest = con.prepareStatement("SELECT * FROM guest WHERE userid = ?");
                prepGuest.setString(1, userId);
                ResultSet guestResult = prepGuest.executeQuery();
                if(!guestResult.next()) {
                    throw new NoResultsFound();
                }

                String guestId = guestResult.getString("guestid");
                MembershipLevel membershipLevel = StringToEnum.toMembershipLevel(guestResult.getString("membershipLevel"));
                int points = guestResult.getInt("points");

                Guest guest = new Guest(userId, guestId, password, nama, umur, email, phone, address, membershipLevel, points);
                return guest;
            } else if (type.equalsIgnoreCase("STAFF")) {
                PreparedStatement prepStaff = con.prepareStatement("SELECT * FROM staff WHERE userid = ?");
                prepStaff.setString(1, userId);
                ResultSet staffResult = prepStaff.executeQuery();
                if(!staffResult.next()) {
                    throw new NoResultsFound();
                }

                String employeeId = staffResult.getString("employeeid");
                double salary = staffResult.getDouble("salary");
                Department department = StringToEnum.toDepartment(staffResult.getString("department"));

                Staff staff = new Staff(userId, password, nama, umur, email, phone, address, employeeId, salary,  department);
                return staff;
            } else if (type.equalsIgnoreCase("ADMIN")) {
                PreparedStatement prepAdmin = con.prepareStatement("SELECT * FROM staff WHERE userid = ?");
                prepAdmin.setString(1, userId);
                ResultSet adminResult = prepAdmin.executeQuery();
                if(!adminResult.next()) {
                    throw new NoResultsFound();
                }

                String employeeId = adminResult.getString("employeeid");
                double salary = adminResult.getDouble("salary");
                Department department = StringToEnum.toDepartment(adminResult.getString("department"));

                Admin admin = new Admin(userId, password, nama, umur, email, phone, address, employeeId, salary);
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String addUser(String password, String name, int umur, String email, String phone, String address, UserType type) {
        try {
            PreparedStatement insertPrepUser = con.prepareStatement("INSERT INTO users (password, nama, umur, email, phone, address, type, userid) VALUES (?, ?, ?, ?, ?, ?, cast(? as user_type), ?)");
            insertPrepUser.setString(1, password);
            insertPrepUser.setString(2, name);
            insertPrepUser.setInt(3, umur);
            insertPrepUser.setString(4, email);
            insertPrepUser.setString(5, phone);
            insertPrepUser.setString(6, address);
            insertPrepUser.setString(7, type.name());
            String uuid = GenerateUUID.generateUUID();
            insertPrepUser.setString(8, uuid);
            insertPrepUser.executeUpdate();
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
