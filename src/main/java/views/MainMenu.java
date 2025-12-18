package views;

import controllers.BookingControllers;
import exceptions.NoResultsFound;
import models.Booking;
import views.admin.AdminBookingView;

import java.util.List;

public class MainMenu {
    BookingControllers controller = new BookingControllers();
    AdminBookingView view = new AdminBookingView();

    public MainMenu() throws NoResultsFound {
    }

    public void showBookings() {
        List<Booking> bookings = BookingControllers.showAllBooking();
        view.displayAllBookings();
    }

}
