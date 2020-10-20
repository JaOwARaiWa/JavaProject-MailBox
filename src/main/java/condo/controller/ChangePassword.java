package condo.controller;

import condo.model.User;
import condo.process.Account;
import condo.process.AccountProcessor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePassword
{
    @FXML Button backBtn, changeBtn;
    @FXML Label progressLabel, usernameLabel;
    @FXML PasswordField oldPassField, newPassField, confirmField;
    private User currentuser;
    private String oldPass;
    private String newPass;
    private String confirm;
    private Account account = new AccountProcessor();

    @FXML public void initialize()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
                usernameLabel.setText(currentuser.getUsername());
            }
        });
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        if (currentuser.getId().equals("ADMIN"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminmenu.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            AdminMenu menu = loader.getController();
            menu.setCurrentuser(currentuser);

            stage.show();
        }
        else if (currentuser.getId().equals("STAFF"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting_staff.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();

            stage.show();
        }
        else if (currentuser.getId().equals("RESIDENT"))
        {
            Button b = (Button) event.getSource();
            Stage stage = (Stage) b.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/setting_resident.fxml"));
            stage.setScene(new Scene(loader.load(), 800, 600));

            Setting sett = loader.getController();

            stage.show();
        }
    }

    @FXML public void handleChangeBtnOnAction(ActionEvent event) throws IOException
    {
        oldPass = oldPassField.getText();
        newPass = newPassField.getText();
        confirm = confirmField.getText();

        System.out.println(oldPass);
        System.out.println(newPass);
        System.out.println(confirm);

        if (oldPass.equals(currentuser.getPassword()) && newPass.equals(confirm))
        {
            account.changePass(currentuser, newPass);
            progressLabel.setText("Your password has changed");
        }
        else
        {
            progressLabel.setText("Wrong user or mismatch confirm password, please try again");
        }
    }

    public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }

}
