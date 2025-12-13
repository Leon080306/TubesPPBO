import controller.UserController;
import exceptions.NoResultsFound;
import models.Guest;
import models.Users;
import repository.UserRepository;
import views.LoginView;
import views.guest.GuestMainMenu;

public class App {
    public static void main(String[] args) throws NoResultsFound {
        new LoginView();
    }
}