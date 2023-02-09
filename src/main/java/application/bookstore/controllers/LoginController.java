package application.bookstore.controllers;

import application.bookstore.models.User;
import application.bookstore.views.Login;
import application.bookstore.views.MainView;
import application.bookstore.views.View;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.logging.Level;

public class LoginController {
    private final Stage primaryStage;
    private View nextView;
    private User currentUser;

    public LoginController(Login view, Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        addListener(view);
    }

    private void addListener(Login view) {
        // lambda function
        view.getView().setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                view.get_login_().fire();
            }
        });

        view.get_login_().setOnAction(e -> {
            String password = view.get_password_().getText();
            String username = view.getUsernameField().getText();
            User potentialUser = new User(username, password);
            if ((currentUser = User.getIfExists(potentialUser)) != null) {
                view.setCurrentUser(currentUser);
                ControllerCommon.LOGGER.log(Level.INFO, "Logged in with user: " + currentUser);
                primaryStage.setResizable(true);
                nextView = new MainView(primaryStage);
                primaryStage.setScene(new Scene(nextView.getView(), MainView.width, MainView.height));
                primaryStage.centerOnScreen();
            } else
                ControllerCommon.error(view.get_error_(), "Wrong username or password");
        });
    }
}
