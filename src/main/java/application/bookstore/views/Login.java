package application.bookstore.views;

import application.bookstore.ui.LoginButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Login extends View {
    public static int width = 500;
    public static int height = 500;

    private final BorderPane _pane_ = new BorderPane();

    private final TextField usernameField = new TextField();
    private final PasswordField _password_ = new PasswordField();
    private final LoginButton _login_ = new LoginButton();

    private final Label _error_ = new Label("");

    public Login() {
        setView();
    }

    private void setView() {
        HBox usernameBox = new HBox();
        Text _usernameLabel = new Text("Username");
        usernameBox.getChildren().addAll(_usernameLabel, usernameField);
        usernameBox.setAlignment(Pos.CENTER);
        usernameBox.setSpacing(10);
        _usernameLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        _usernameLabel.setFill(Color.BLACK);

        HBox passwordBox = new HBox();
        Text _passwordLabel = new Text("Password");
        passwordBox.getChildren().addAll(_passwordLabel, _password_);
        passwordBox.setSpacing(10);
        passwordBox.setAlignment(Pos.CENTER);
        _passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        _passwordLabel.setFill(Color.BLACK);

        _error_.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setPadding(new Insets(20));
        Pane spacer = new Pane();
        spacer.setMinWidth(20);
        vBox.getChildren().addAll(usernameBox, passwordBox, _login_, _error_, spacer);

        _pane_.setCenter(vBox);

    }

    @Override
    public Parent getView() {
        return _pane_;
    }


    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField get_password_() {
        return _password_;
    }

    public Button get_login_() {
        return _login_;
    }

    public Label get_error_() {
        return _error_;
    }
}
