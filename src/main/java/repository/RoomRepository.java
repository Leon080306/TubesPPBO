
package repository;

import controller.RoomController;
import models.Room;
import models.enums.RoomType;
import models.enums.UserType;
import utils.Database;
import utils.GenerateUUID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private static final Connection con = Database.connect();

    public static boolean addRoom(String roomNumber, RoomType roomType, String roomDescription, double roomPrice) {
        try {
            PreparedStatement insertRoom = con.prepareStatement("INSERT INTO room (roomid, roomnumber, roomtype, roomdescription, roomprice) VALUES (?, ?, cast(? as room_type), ?, ?)");
            insertRoom.setString(1, GenerateUUID.generateUUID());
            insertRoom.setString(2, roomNumber);
            insertRoom.setString(3, roomType.name());
            insertRoom.setString(4, roomDescription);
            insertRoom.setDouble(5, roomPrice);
            return insertRoom.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Room getRoomByRoomId(String roomId) {
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM room WHERE roomid = ?");
            pstmt.setString(1, roomId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return new Room(rs.getString("roomid"), rs.getString("roomnumber"), RoomType.valueOf(rs.getString("roomtype").toUpperCase()), rs.getString("roomdescription"), rs.getDouble("roomprice"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Room getRoomByRoomNumber(String roomNumber){
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM room WHERE roomnumber = ?");
            pstmt.setString(1, roomNumber);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return new Room(rs.getString("roomid"), rs.getString("roomnumber"), RoomType.valueOf(rs.getString("roomtype").toUpperCase()), rs.getString("roomdescription"), rs.getDouble("roomprice"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteRoom(String roomId) {
        try {
            PreparedStatement psmt = con.prepareStatement("DELETE FROM room WHERE roomid = ?");
            psmt.setString(1, roomId);
            return psmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateRoom(String roomNumber, RoomType roomType, String roomDescription, double roomPrice, String roomId) {
        try {
            PreparedStatement psmt = con.prepareStatement("UPDATE room SET roomnumber = ?, roomtype = cast(? as room_type), roomdescription = ?, roomprice = ? WHERE roomid = ?");
            psmt.setString(1, roomNumber);
            psmt.setString(2, roomType.name());
            psmt.setString(3, roomDescription);
            psmt.setDouble(4, roomPrice);
            psmt.setString(5, roomId);
            return psmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM room");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                roomList.add(new Room(rs.getString("roomid"), rs.getString("roomnumber"), RoomType.valueOf(rs.getString("roomtype").toUpperCase()), rs.getString("roomdescription"), rs.getDouble("roomprice")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }
}
