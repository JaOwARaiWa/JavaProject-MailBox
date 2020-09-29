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

public class ChangePassword
{
    @FXML Button backBtn, changeBtn;
    @FXML Label progressLabel;
    @FXML TextField usernameField;
    @FXML PasswordField newPassField, confirmField;
    private String user;
    private String newpass;
    int check;
    private Receiver r;

    @FXML public void initialize()
    {

    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        if (check == 1)
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();
            sett.setUser(user);
            sett.setCheck(1);
            sett.setR(r);

            stage.show();
        }
        else if (check == -1)
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Login in = loader.getController();
            in.setCheck(-1);
            in.setR(r);

            stage.show();
        }

    }

    @FXML public void handleChangeBtnOnAction(ActionEvent event) throws IOException
    {
        System.out.println(usernameField.getText());
        System.out.println(newPassField.getText());
        System.out.println(confirmField.getText());

        if (newPassField.getText().equals(confirmField.getText()))
        {
            newpass = newPassField.getText();
            r.changePassword(newpass);
            progressLabel.setText("Your password have changed");
            usernameField.setText("");
            newPassField.setText("");
            confirmField.setText("");
        }
        else
        {
            progressLabel.setText("Confirm password is not match, Please try again");
        }

        /*
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/changepassword.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        ChangePassword chng = loader.getController();
        chng.setUser(user);
        chng.setCheck(1);
        chng.setR(r);

        stage.show();
        */
    }

    public void setUser(String user)
    {
        this.user = user;
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
