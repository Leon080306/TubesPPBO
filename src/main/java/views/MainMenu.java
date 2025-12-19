package views;

import controller.BookingController;
import exceptions.NoResultsFound;
import models.Booking;
import views.admin.AdminBookingView;

import java.util.List;

public class MainMenu {
    BookingController controller = new BookingController();
    AdminBookingView view = new AdminBookingView();

    public MainMenu() throws NoResultsFound {
    }

    public void showBookings() {
        List<Booking> bookings = BookingController.showAllBooking();
        view.displayAllBookings();
    }

}
