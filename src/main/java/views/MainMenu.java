package views;

import controllers.BookingControllers;
import exceptions.NoResultsFound;
import models.Booking;

import javax.swing.*;
import java.util.List;

public class MainMenu {
    BookingControllers controller = new BookingControllers();
    BookingView view = new BookingView();

    public MainMenu() throws NoResultsFound {
    }

    public void showBookings() {
        try {
            List<Booking> bookings = controller.showAllBooking();
            view.displayAllBookings(bookings);
        } catch (NoResultsFound e) {
            System.out.println("⚠ No bookings found.");
        }
    }

}
