package condo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login
{
    @FXML Button loginBtn, creditBtn, changePassBtn;
    @FXML Label faultLabel;
    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    private String user, username, password;
    int check;
    private Address a;
    private Receiver r;

    @FXML public void initialize()
    {
        setCheck(check);

        if (check == -1)
        {
            username = r.getId();
            password = r.getPassword();
            System.out.println(r.getPassword());
            setCheck(-1);
        }
        else if (check == 0)
        {
            a = new Address('A', '1', "01");
            r = new Receiver("First", a);
            r.setId(a);
            r.setPassword("0123");
            username = r.getId();
            password = r.getPassword();
            System.out.println(r.getPassword());
        }
    }

    @FXML public void handleLoginBtnOnAction(ActionEvent event) throws IOException
    {
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());

        if (passwordField.getText().equals(password) && usernameField.getText().equals(username) || (passwordField.getText().equals("-1") && usernameField.getText().equals("-1")))
        {
            user = r.getName();
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/usermenu.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            UserMenu menu = loader.getController();
            menu.setUser(user);
            menu.setCheck(1);
            menu.setR(r);

            stage.show();
        }
        else
        {
            faultLabel.setText("Wrong user or password, please try again");
        }
    }

    @FXML public void handleCreditBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/developer.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        Developer dev = loader.getController();
        dev.setCheck(-1);

        stage.show();
    }

    @FXML public void handleChangePassBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changepassword.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChangePassword chg = loader.getController();
        chg.setCheck(-1);
        chg.setR(r);

        stage.show();
    }

    public void setCheck(int check)
    {
        this.check = check;
    }

    public void setR(Receiver r)
    {
        this.r = r;
    }
}
