package condo.controller;

import condo.model.User;
import condo.process.Account;
import condo.process.AccountProcessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddStaff
{
    @FXML TextField nameField, surnameField, usernameField;
    @FXML PasswordField passwordField, confirmField;
    @FXML Button createBtn, backBtn;
    private Alert popup = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
    private User currentuser;
    private Account account = new AccountProcessor();
    private ArrayList<String> newAccount = new ArrayList<>();

    @FXML public void initialize()
    {

    }

    @FXML public void handleCreateBtnOnAction(ActionEvent event) throws IOException
    {
        if (nameField.getText().equals("") ||
            surnameField.getText().equals("") ||
            usernameField.getText().equals("") ||
            passwordField.getText().equals("") ||
            confirmField.getText().equals("") ||
            !confirmField.getText().equals(passwordField.getText()))
        {
            popup.setAlertType(Alert.AlertType.ERROR);
            popup.setTitle("Creating failed");
            popup.setHeaderText("Failed");
            popup.setContentText("Please fill all the field or check your password");
            popup.showAndWait();
        }
        else
        {
            popup.setHeaderText("Do you want to create staff?");
            popup.setContentText("Name : " + nameField.getText() +
                    "\nSurname : " + surnameField.getText() +
                    "\nUsername : " + usernameField.getText() +
                    "\nPassword : " + passwordField.getText());
            popup.showAndWait();

            if (popup.getResult() == ButtonType.YES)
            {
                newAccount.add(usernameField.getText());
                newAccount.add(passwordField.getText());
                newAccount.add(nameField.getText());
                newAccount.add(surnameField.getText());
                newAccount.add("None");
                newAccount.add("None");

                if (account.addStaff(newAccount))
                {
                    account.writeNewStaff(newAccount);
                    popup.setAlertType(Alert.AlertType.NONE);
                    popup.setTitle("Creating successful");
                    popup.setHeaderText("Successful");
                    popup.setContentText("You have created " + newAccount.get(2) + " a staff");
                }
                else
                {
                    popup.setAlertType(Alert.AlertType.ERROR);
                    popup.setTitle("Creating failed");
                    popup.setHeaderText("Failed");
                    popup.setContentText(newAccount.get(0) + " is already used, please change the username");

                }
                popup.showAndWait();
                nameField.setText("");
                surnameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
                confirmField.setText("");
                while (newAccount.size() != 0)
                {
                    newAccount.remove(0);
                }
            }
        }
        popup.setAlertType(Alert.AlertType.CONFIRMATION);
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminmenu.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        AdminMenu menu = loader.getController();
        menu.setCurrentuser(currentuser);

        stage.show();
    }

    public void setCurrentuser(User currentuser)
    {
        this.currentuser = currentuser;
    }
}