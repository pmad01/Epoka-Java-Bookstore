package application.bookstore;

import application.bookstore.controllers.ControllerCommon;
import application.bookstore.controllers.LoginController;
import application.bookstore.models.*;
import application.bookstore.views.Login;
import application.bookstore.views.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/* Application Logic */

public class Main extends Application {

    public static void main(String[] args) {
        FileHandler _fileHandler_;
        try {
            _fileHandler_ = new FileHandler("data/log.txt");
            ControllerCommon.LOGGER.addHandler(_fileHandler_);
            SimpleFormatter formatter = new SimpleFormatter();
            _fileHandler_.setFormatter(formatter);
            ControllerCommon.LOGGER.info("Starting APP...");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        loadData();
        /* create user:admin password:admin and sample data
        if it is the first time running (data/users.ser does not exist) */
        createAdminAndData();
        launch(args);
    }

    public static void createAdminAndData(){
        // create new user.ser file in data folder
        File f = new File(User.FILE_PATH);
        // create file if it does not exist
        if (!f.exists()){
            // log the starting process for debug
            ControllerCommon.LOGGER.info("Starting");
            new File(Skeleton.FOLDER_PATH).mkdirs();
            // create nwe user as admin
            User _admin = new User("admin", "admin", Role.ADMIN);
            // log into file
            ControllerCommon.LOGGER.log(Level.INFO, _admin.saveInFile());
            // create new user as manager
            User _manager = new User("manager", "manager", Role.MANAGER);
            ControllerCommon.LOGGER.log(Level.INFO, _manager.saveInFile());

            User _librarian = new User("librarian", "librarian", Role.LIBRARIAN);
            ControllerCommon.LOGGER.log(Level.INFO, _librarian.saveInFile());

            Author a = new Author("Albert", "Camus");
            // save author in data folder
            a.saveInFile();
            // Book(isbn, name, quantity, purchased_price, selling_price, Author Class)
            Book b = new Book("1234567890120", "The Stranger", 20, 5, 5.2f, a);
            // save book in data folder
            b.saveInFile();
            Book c = new Book("1234567890121", "The Plague", 8, 7, 7.5f, a);
            c.saveInFile();
            Book d = new Book("1234567890122", "The Myth of Sisyphus", 10, 6, 6.4f, a);
            d.saveInFile();
            Book e = new Book("1234567890123", "The Fall", 14, 5, 5.2f, a);
            e.saveInFile();
            Book h = new Book("1234567890123", "The Fall", 12, 5, 5.2f, a);
            h.saveInFile();
            Author g =new Author("Fyodor", "Dostoevsky");
            g.saveInFile();
            Book i = new Book("1234567890124", "Crime and Punishment", 15, 5, 5.2f, a);
            i.saveInFile();
            Book j = new Book("1234567890125", "The Brothers Karamazov", 10, 6, 6.3f, a);
            j.saveInFile();
        }
    }

    private static void loadData(){
        ControllerCommon.LOGGER.info("Loading data Files...");
        User.getUsers();
        Author.getAuthors();
        Book.getBooks();
        Order.getOrders();
    }

    @Override
    public void start(Stage stage) {
        Login login = new Login();
        new LoginController(login, stage);
        Scene scene = new Scene(login.getView(), MainView.width, MainView.height);
        stage.setTitle("Bookstore");
        stage.setScene(scene);
        stage.show();
    }

}
