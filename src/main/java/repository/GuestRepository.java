package repository;

import models.enums.MembershipLevel;
import utils.Database;
import utils.GenerateUUID;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GuestRepository {
    private static final Connection con = Database.connect();

    public static boolean addGuest(String password, String name, int umur, String email, String phone, String address, String type) {
        try {
            String uuid = UserRepository.addUser(password, name, umur, email, phone, address, type);
            PreparedStatement insertGuest = con.prepareStatement("INSERT INTO guest (guestid, membershipLevel, points, userid) VALUES (?, ?, ?, ?)");
            insertGuest.setString(1, GenerateUUID.generateUUID());
            insertGuest.setString(2, MembershipLevel.REGULAR.name());
            insertGuest.setInt(3, 0);
            insertGuest.setString(4, uuid);
            insertGuest.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
