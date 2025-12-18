package repository;

import controller.UserController;
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
import utils.StringToEnum;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestRepository {
    private static final Connection con = Database.connect();

    public static Guest getGuestByGuestId(String guestId) {
        try {
            PreparedStatement psmt = con.prepareStatement("SELECT * FROM guest WHERE guestid = ?");
            psmt.setString(1, guestId);
            ResultSet rs = psmt.executeQuery();
            rs.next();
            return (Guest) UserController.getUserDataByUserId(rs.getString("userid"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean udpateGuestData(int points, MembershipLevel membershipLevel, String userId) {
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE guest SET membershiplevel = cast(? as  membershiplevel_type), points = ? WHERE userid = ?");
            pstmt.setString(1, membershipLevel.name());
            pstmt.setInt(2, points);
            pstmt.setString(3, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addGuest(String password, String name, int umur, String email, String phone, String address, MembershipLevel membershipLevel, int points) {
        try {
            String uuid = UserRepository.addUser(password, name, umur, email, phone, address, UserType.GUEST);
            PreparedStatement insertGuest = con.prepareStatement("INSERT INTO guest (guestid, membershipLevel, points, userid) VALUES (?, cast(? as membershiplevel_type), ?, ?)");
            insertGuest.setString(1, GenerateUUID.generateUUID());
            insertGuest.setString(2, membershipLevel.name());
            insertGuest.setInt(3, points);
            insertGuest.setString(4, uuid);

            return insertGuest.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
